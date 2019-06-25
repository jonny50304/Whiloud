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
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import init.GlobalService;
import login.service.LoginService;
import register.model.MemberBean;
import register.service.MemberService;

/**
 * Servlet implementation class CheckPassWordServletJSON
 */
@WebServlet("/CheckPasswordServletJSON")
public class CheckPasswordServletJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> error = new ArrayList<>();
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		
		ServletContext sc  =getServletContext();
		WebApplicationContext ctx  =  WebApplicationContextUtils.getWebApplicationContext(sc);
		LoginService loginService =ctx.getBean(LoginService.class);
		
		String account = LoginMemberBean.getAccount();
		String password = request.getParameter("password");
		System.out.println("收到的password: " + password);
		// 將密碼加密兩次，以便與存放在表格內的密碼比對
		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		System.out.println("加密的password: " + password);
		
		MemberBean mb = null;
		try {
			mb = loginService.checkIDPassword(account, password);
			if (mb != null) {
				// OK, 檢查通過
				error.add("none");
			} else {
				// NG, 檢查失敗
				error.add("passwordError");
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
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
