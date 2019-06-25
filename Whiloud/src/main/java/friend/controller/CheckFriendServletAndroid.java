package friend.controller;

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

import friend.model.FriendBean;
import friend.service.FriendService;
@WebServlet("/CheckFriendServletAndroid")
public class CheckFriendServletAndroid extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入CheckFriendServletAndroid");
		Integer memberNo;
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObjectReq = gson.fromJson(jsonIn.toString(), JsonObject.class);
		memberNo = jsonObjectReq.get("memberNo").getAsInt();
		ServletContext sc = getServletContext();
		WebApplicationContext  ctx  =  WebApplicationContextUtils.getWebApplicationContext(sc);
		FriendService fs = ctx.getBean(FriendService.class);
		List<FriendBean> friendBeanList = fs.getAllFriends(memberNo);
		String jsonOut = new JSONArray(friendBeanList).toString();
		System.out.println("List to JSON: " + jsonOut);
		writeText(response,jsonOut);
	}
	
	
    private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}


}
