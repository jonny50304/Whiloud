﻿package login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import init.GlobalService;
import login.service.LoginService;
import register.model.MemberBean;

@WebServlet(urlPatterns={"/login/loginOld.do", "/login.do"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//取得session物件
		HttpSession session = request.getSession();
		// 定義存放錯誤訊息的Map物件
		Map<String, String> errorMsgMap = new HashMap<String, String>();

		// 將errorMsgMap放入request物件內，識別字串為 "ErrorMsgKey"
		request.setAttribute("ErrorMsgKey", errorMsgMap);
		// 1. 讀取使用者輸入資料
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String rm = request.getParameter("rememberMe");
		String au = request.getParameter("autoLogin");
		String requestURI = (String) session.getAttribute("requestURI");
		// 2. 進行必要的資料轉換
		// 無
		// 3. 檢查使用者輸入資料
		// 如果 userId 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (account == null || account.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}
		// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}

		// 如果 errorMsgMap 不是空的，表示有錯誤，交棒給login.jsp
		if (!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}

		// **********Remember Me****************************
		Cookie cookieUser = null;
		Cookie cookiePassword = null;
		Cookie cookieRememberMe = null;
		Cookie cookieAutoLogin = null;
//		 rm存放瀏覽器送來之RememberMe的選項，如果使用者對RememberMe打勾，rm就不會是null
		
		if(au !=null) {
			cookieUser = new Cookie("user", account);
			cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());
			
			cookieAutoLogin = new Cookie("au", "true");
			cookieAutoLogin.setMaxAge(7 * 24 * 60 * 60);
			cookieAutoLogin.setPath(request.getContextPath());
		} else {
			cookieAutoLogin = new Cookie("au", "true");
			cookieAutoLogin.setMaxAge(0);
			cookieAutoLogin.setPath(request.getContextPath());
		}
		
		if (rm != null) {
			cookieUser = new Cookie("user", account);
			cookieUser.setMaxAge(7 * 24 * 60 * 60); // Cookie的存活期: 七天
			cookieUser.setPath(request.getContextPath());

			String encodePassword = GlobalService.encryptString(password);
			cookiePassword = new Cookie("password", encodePassword);
			cookiePassword.setMaxAge(7 * 24 * 60 * 60);
			cookiePassword.setPath(request.getContextPath());

			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(7 * 24 * 60 * 60);
			cookieRememberMe.setPath(request.getContextPath());
			
			
		} else{ // 使用者沒有對 RememberMe 打勾
			cookieRememberMe = new Cookie("rm", "true");
			cookieRememberMe.setMaxAge(0);
			cookieRememberMe.setPath(request.getContextPath());
			
			if(au==null) {
				cookieUser = new Cookie("user", account);
				cookieUser.setMaxAge(0); // MaxAge==0 表示要請瀏覽器刪除此Cookie
				cookieUser.setPath(request.getContextPath());

				String encodePassword = GlobalService.encryptString(password);
				cookiePassword = new Cookie("password", encodePassword);
				cookiePassword.setMaxAge(0);
				cookiePassword.setPath(request.getContextPath());
			}
		}
		response.addCookie(cookieUser);
		response.addCookie(cookiePassword);
		response.addCookie(cookieRememberMe);
		response.addCookie(cookieAutoLogin);
//		 ********************************************

		// 4. 進行 Business Logic 運算
		// 將LoginServiceImpl類別new為物件，存放物件參考的變數為 loginService
		ServletContext sc  =getServletContext();
		WebApplicationContext ctx  =  WebApplicationContextUtils.getWebApplicationContext(sc);
		LoginService loginService =ctx.getBean(LoginService.class);


		// 將密碼加密兩次，以便與存放在表格內的密碼比對
		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		MemberBean mb = null;
		try {
			// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
			mb = loginService.checkIDPassword(account, password);
			if (mb != null) {
				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
				session.setAttribute("LoginOK", mb);
			} else {
				// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
				errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
			}
		} catch (RuntimeException ex) {
			errorMsgMap.put("LoginError", ex.getMessage());
		}

		// 5.依照 Business Logic 運算結果來挑選適當的畫面
		// 如果 errorMsgMap 是空的，表示沒有任何錯誤，交棒給下一棒
		if (errorMsgMap.isEmpty()) {
//			if (requestURI != null) {
//				System.out.println("requestURI=" + requestURI);
//				System.out.println("登入成功1");
//				requestURI = (requestURI.length() == 0 ? request.getContextPath() : requestURI);
//				response.sendRedirect(response.encodeRedirectURL(requestURI));
//				return;
//			} else {
				System.out.println("登入成功2");
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/main.jsp"));
				return;
//			}
		} else {
			// 如果errorMsgMap不是空的，表示有錯誤，交棒給index
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
			return;
		}
	}
}