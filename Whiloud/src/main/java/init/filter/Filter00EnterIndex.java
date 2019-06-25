package init.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import init.GlobalService;
// 本過濾器監控 /_02_login/login.jsp。
// 當使用者要求此jsp時，容器會將本jsp網頁轉譯為java程式，接者編譯為類別檔，然後載入並執行此jsp。
// 當容器執行jsp之前會先執行本過濾器。其目的是要檢視瀏覽器送來的Cookie中是否含有帳、密等資料。
// 如果有，取出來，將密碼解密，然後存入Request物件內，以便jsp能將其加入相關之input標籤的value屬性內。
import login.service.LoginService;
import register.model.MemberBean;

@WebFilter("/index.jsp")
public class Filter00EnterIndex implements Filter {
	String requestURI;
	public Filter00EnterIndex() {

	}	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Filter: Filter00EnterIndex");
		// 容器會在遠方客戶端提出請求、要求容器執行_02_login/login.jsp前，先執行本程式
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			
//			try {
//				HttpSession session1 = req.getSession(false);
//				MemberBean LoginMemberBean = (MemberBean) session1.getAttribute("LoginOK");
//				System.out.println("已存在session，不用進入index.jsp");
//				res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/main.jsp"));
//
//			}catch(Exception e) {
//				System.out.println("沒抓取到session");
//			}
			
			// **********Remember Me****************
			String cookieName = "";
			String user = "";
			String password = "";
			String rememberMe = "";
			String au = "";
			// 取出瀏覽器送來的Cookie
			Cookie[] cookies = req.getCookies();
			
			//判斷是不是rememberMe
			if (cookies != null) {   						// 如果含有Cookie

				for (int i = 0; i < cookies.length; i++) {	// 檢視每個Cookie
					cookieName = cookies[i].getName();
					if (cookieName.equals("user")) {
						//找到user這個Cookie
						user = cookies[i].getValue();
						System.out.println("找到user: " + user);
					}
					else if (cookieName.equals("password")) {
						//找到password這個Cookie						
						String tmp  = cookies[i].getValue();
						// 將密碼解密
						if (tmp!= null){
							password = 	GlobalService.decryptString(
									        GlobalService.KEY, tmp);
							System.out.println("找到password: " + password);
						}
					} 
					else if (cookieName.equals("rm")) {
						//找到rm這個Cookie(rm: rememberMe)
						rememberMe = cookies[i].getValue();
					}
				}
			} else {
				// 找不到Cookie，沒有關係，就讓使用者輸入資料即可。
			}
			
			//判斷有沒有autoLogin
			if (cookies != null) {   						// 如果含有Cookie

				for (int i = 0; i < cookies.length; i++) {
					cookieName = cookies[i].getName();
					if (cookieName.equals("au")) {
						au = cookies[i].getValue();
						System.out.println("找到au=" + au);
						
						//如果au=true準備開始自動登入
						if(au.equals("true")) {
							ServletContext sc  = req.getServletContext();
							WebApplicationContext ctx  =  WebApplicationContextUtils.getWebApplicationContext(sc);
							LoginService loginService =ctx.getBean(LoginService.class);

							// 將密碼加密兩次，以便與存放在表格內的密碼比對
							password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
							MemberBean mb = null;
							Map<String, String> errorMsgMap = new HashMap<String, String>();
							try {
								// 呼叫 loginService物件的 checkIDPassword()，傳入userid與password兩個參數
								mb = loginService.checkIDPassword(user, password);
								if (mb != null) {
									// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"
									HttpSession session2 = req.getSession();
									session2.setAttribute("LoginOK", mb);
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
								System.out.println("登入成功");
								res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/main.jsp"));
								return;
							} else {
								// 如果errorMsgMap不是空的，表示有錯誤，交棒給index
								RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
								rd.forward(request, response);
								return;
							}
						}
					}
				}
				
			}
			
			
			
			
			// 將這三項資料存入request物件
			request.setAttribute("rememberMe", rememberMe);
			request.setAttribute("user", user);
			request.setAttribute("password", password);
		}
		chain.doFilter(request, response);   // 請容器執行下一棒程式
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}
	
}
