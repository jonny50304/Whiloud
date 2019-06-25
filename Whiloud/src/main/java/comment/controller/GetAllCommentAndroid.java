package comment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import comment.model.CommentBean;
import comment.service.CommentService;
import post.model.PostBean;
@WebServlet("/GetAllCommentAndroid")
public class GetAllCommentAndroid extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入getAllCommentAndroid");
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
		Integer postNo;
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		postNo =  jsonObject.get("postNo").getAsInt();
		ServletContext sc  =getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		CommentService cs  = ctx.getBean(CommentService.class);
	
		List<CommentBean> cbl  =	cs.getCommentByPostNo(postNo);
		for(CommentBean cb:cbl) {
			cb.setPb(null);
		}
		String jsonOut = new JSONArray(cbl).toString();
		System.out.println("List to JSON: " + jsonOut);
		writeText(response,jsonOut);

		
	}
	 @SuppressWarnings("unused")
	private void writeText(HttpServletResponse response, String outText) throws IOException {
			response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.print(outText);
			System.out.println("output: " + outText);
		}
}
