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
 * Servlet implementation class AcceptRequestServlet
 */
@WebServlet("/friend/AcceptRequestServlet")
public class OLDAcceptRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OLDAcceptRequestServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入/friend/AcceptRequestServlet");
		request.setCharacterEncoding("UTF-8");
		Integer memberNo = Integer.valueOf(request.getParameter("memberNo"));
		Integer friendNo = Integer.valueOf(request.getParameter("friendNo"));
		ServletContext sc   =getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		FriendRequestService frs = ctx.getBean(FriendRequestService.class);
		MemberBean mb1 = ctx.getBean(MemberService.class).getMember(String.valueOf(memberNo));
		MemberBean mb2 = ctx.getBean(MemberService.class).getMember(String.valueOf(friendNo));
		frs.becomeFriend(mb1, mb2);
		
		RequestDispatcher rd = request.getRequestDispatcher("/test/myFriendTest.jsp");
		rd.forward(request, response);
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
