package friend.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import friend.model.FriendBean;
import friend.model.FriendRequestBean;
import friend.service.FriendRequestService;
import register.model.MemberBean;

/**
 * Servlet implementation class GetFriendRequestServletJSON
 */
@WebServlet("/GetFriendRequestServletJSON")
public class GetFriendRequestServletJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("------GetFriendRequestServletJSON------");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		Integer memberNo = LoginMemberBean.getMemberNo();
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
		FriendRequestService frs = ctx.getBean(FriendRequestService.class);
		List<FriendRequestBean> fbl = frs.getAllWasInvited(memberNo);
		
		String jsonOut = new JSONArray(fbl).toString();
		System.out.println("轉換JSON成功");
		System.out.println("List to JSON: " + jsonOut);
		
		// 送回客戶端
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
