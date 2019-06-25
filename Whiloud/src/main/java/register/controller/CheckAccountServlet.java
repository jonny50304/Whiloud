package register.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

import register.service.MemberService;

/**
 * Servlet implementation class CheckAccountServlet
 */
@WebServlet("/CheckAccountServlet")
public class CheckAccountServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CheckAccountServlet");
		request.setCharacterEncoding("UTF-8");
		String account =  request.getParameter("account");
		String nickname = request.getParameter("nickname");
		List<String> error = new ArrayList<>();
		boolean flag = false;
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService service = ctx.getBean(MemberService.class);
		
		
		if (service.idExists(account)) {
			error.add("accountExist");
			flag = true;
		} 
		if (service.nicknameExists(nickname)) {
			error.add("nicknameExist");
			flag = true;
		}
		if(!flag) {
			error.add("none");
		}
		
		String jsonOut = new JSONArray(error).toString();
		System.out.println("轉換JSON成功");
		System.out.println("List to JSON: " + jsonOut);
		
		writeText(response, jsonOut);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
