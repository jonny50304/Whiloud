package register.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import init.GlobalService;
import register.model.MemberBean;
import register.service.MemberService;
import register.service.impl.MemberServiceImpl_Hibernate;
@WebServlet("/registerAndroid")
public class RegisterServletAndroid extends HttpServlet{
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	public RegisterServletAndroid() {
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletContext sc  =getServletContext();
		WebApplicationContext ctx  =  WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService memberService =ctx.getBean(MemberService.class);

		
		String errorEmail ="";
		String errorPassword="";
		String errorName="";
		String errorPhone="";
		String errorGender="";
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		Gson gson = new Gson();
		//讀請求送來的資訊	
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
		MemberBean   mb =  new Gson().fromJson(jsonObjectReq.get("memberBean").getAsString(), MemberBean.class);
		String password = mb.getPASSWORD();
		System.out.println(mb.getGender());
		if(mb.getAccount().trim().length()==0||mb.getAccount() ==null) {
			errorEmail =  "帳號必須輸入";
		}
		if(mb.getPASSWORD().trim().length()==0||mb.getAccount()==null) {
			errorPassword = "密碼必須輸入";
		}
		if(mb.getNickname().trim().length()==0||mb.getNickname()==null) {
			errorName = "暱稱必須輸入";
		}
		if(mb.getPhone().trim().length()==0||mb.getPhone()==null) {
			errorPhone = "電話欄必須輸入";
		}
		if(mb.getGender().trim().length()==0||mb.getGender()==null) {
			errorGender = "性別必須勾選";
		}
		 if(memberService.idExists(mb.getAccount())) {
			 errorEmail =  "帳號重複";
		 };
		 if(memberService.nicknameExists(mb.getNickname())) {
			 errorName  = "暱稱重複,請重新輸入";
		 }
		 //註冊成功
		if(errorEmail =="" &&errorPassword==""&&errorName==""&&errorPhone==""&&errorGender=="") {
			//成功時密碼二次加密
			password = GlobalService.getMD5Endocing(
					GlobalService.encryptString(password));
			mb.setPASSWORD(password);
			mb.setPicturePath("/data/memberPicture/default.png");
		memberService.saveMember(mb);
			//送回客戶端
		writeText(response, gson.toJson(mb));
		//失敗
		}else {
			JsonObject	jsonObjectResp = new JsonObject();
			jsonObjectResp.addProperty("errorEmail", errorEmail);
			jsonObjectResp.addProperty("errorPassword", errorPassword);
			jsonObjectResp.addProperty("errorName", errorName);
			jsonObjectResp.addProperty("errorPhone", errorPhone);
			jsonObjectResp.addProperty("errorGender", errorGender);
			System.out.println(errorEmail);
			System.out.println(errorPassword);
			System.out.println(errorName);
			System.out.println(errorPhone);
			System.out.println(errorGender);
			//送回客戶端
			writeText(response,gson.toJson(jsonObjectResp));
			

		}
	}
    private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
	
}
