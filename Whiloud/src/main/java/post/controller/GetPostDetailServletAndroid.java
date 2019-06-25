package post.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import post.model.RecordBean;
import post.service.RecordService;
@WebServlet("/GetPostDetailServletAndroid")
public class GetPostDetailServletAndroid extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("進入GetPostDetailServletAndroid");
		String successMsg ="success";
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);

		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		String postNo =String.valueOf(jsonObject.get("postNo").getAsInt());
		System.out.println(postNo);
		try {
		ServletContext  sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		RecordService recordservice = ctx.getBean(RecordService.class);
		List<RecordBean> recordList = recordservice.getRecordList(postNo);
		
		//YAO寫的部分
		int recordListSize = recordList.size();
		for(int i=recordListSize-1; i>=0; i--){
			recordList.get(i).setLikeRecordBean(null);
			recordList.get(i).getPb().setLikePostBean(null);
			if(recordList.get(i).getRecordPath() == null) {
				recordList.remove(i);
				System.out.println("remove recordList: " + i);
			}
		}

		
		System.out.println(recordList.size());
		
		
		String jsonOut = new JSONArray(recordList).toString();
		writeText(response,jsonOut);
		
		}catch(Exception e) {
			
		}
		
		
		
	}
	
	
	
	
	
	 @SuppressWarnings("unused")
	private void writeText(HttpServletResponse response, String outText) throws IOException {
			response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.print(outText);
			System.out.println("output: " + outText);
		}

}
