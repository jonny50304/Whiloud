package friend.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import friend.service.FriendRequestService;
import friend.service.impl.FriendRequestServiceImpl;
import register.model.MemberBean;
import register.service.MemberService;
import register.service.impl.MemberServiceImpl_Hibernate;

/**
 * Servlet implementation class SendRequestServlet
 */
@WebServlet("/friend/SendRequestServlet")
public class OLDSendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OLDSendRequestServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入SendRequestServlet");
		request.setCharacterEncoding("UTF-8");
		ServletContext sc = getServletContext();
		WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		Integer memberNo = Integer.parseInt(request.getParameter("memberNo"));
		Integer friendNo = Integer.parseInt(request.getParameter("friendNo"));
		MemberBean mb1 = ctx.getBean(MemberService.class).getMember(String.valueOf(memberNo));
		MemberBean mb2 = ctx.getBean(MemberService.class).getMember(String.valueOf(friendNo));
		
		FriendRequestService frs = ctx.getBean(FriendRequestService.class);
		frs.sendRequest(mb1, mb2);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/test/myFriendTest.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
