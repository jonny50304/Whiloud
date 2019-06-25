package login.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import init.GlobalService;
import login.service.LoginService;
import register.model.MemberBean;
import register.service.MemberService;
import register.service.impl.MemberServiceImpl_Hibernate;
@WebServlet("/loginAndroid")
public class LoginServletAndroid  extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	public LoginServletAndroid() {
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext sc  =getServletContext();
		WebApplicationContext ctx  =  WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService memberService =ctx.getBean(MemberService.class);

	
		MemberBean memberBean = null;
		String account = null;
		String PASSWORD =null;
		String errorLogin =null;
		
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
	
		JsonObject jsonObjectReq = gson.fromJson(jsonIn.toString(), JsonObject.class);
		String action = jsonObjectReq.get("action").getAsString();
		System.out.println(action);
		account = jsonObjectReq.get("account").getAsString();
		PASSWORD= jsonObjectReq.get("PASSWORD").getAsString();
		PASSWORD = GlobalService.getMD5Endocing(GlobalService.encryptString(PASSWORD));
		
		memberBean = memberService.checkIdPassword(account, PASSWORD);
		if (memberBean ==null) {
			errorLogin =  "帳號不存在或帳號密碼輸入錯誤";
			JsonObject	jsonObjectResp = new JsonObject();
			jsonObjectResp.addProperty("errorLogin", errorLogin);
			System.out.println(errorLogin);
			writeText(response,gson.toJson(jsonObjectResp));
		}
		if(memberBean !=null) {
			
			String jout =   new JSONObject(memberBean).toString();
			System.out.println(jout);
			writeText(response, jout);

		}
	}
	 private void writeText(HttpServletResponse response, String outText) throws IOException {
			response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.print(outText);
			System.out.println("output: " + outText);
		}

}
