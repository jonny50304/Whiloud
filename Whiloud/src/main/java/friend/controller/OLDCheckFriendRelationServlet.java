package friend.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import friend.service.FriendService;
import friend.service.impl.FriendRequestServiceImpl;
import friend.service.impl.FriendServiceImpl;
import register.model.MemberBean;
import register.service.impl.MemberServiceImpl_Hibernate;

/**
 * Servlet implementation class CheckFriendRelationServlet
 */
@WebServlet("/friend/CheckFriendRelationServlet")
public class OLDCheckFriendRelationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public OLDCheckFriendRelationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入CheckFriendRelationServlet");
		request.setCharacterEncoding("UTF-8");
		
		Integer memberNo = Integer.parseInt(request.getParameter("memberNo"));
		Integer friendNo = Integer.parseInt(request.getParameter("friendNo"));
		
		ServletContext  sc = getServletContext();
		WebApplicationContext ctx  =WebApplicationContextUtils.getWebApplicationContext(sc);
		FriendService fs = ctx.getBean(FriendService.class);
		Boolean isFriend = fs.isFriend(memberNo,friendNo);
		
		request.setAttribute("isFriend", isFriend);
		System.out.println("memberNo = " + memberNo + ", friendNo = " + friendNo + "\nisFriend = " + isFriend );
		
		if(!isFriend) {
			FriendRequestService frs = ctx.getBean(FriendRequestService.class);
			Boolean isInRequest = 	frs.isInRequest(memberNo, friendNo);
			System.out.println("你的交友邀請是否還在等待對方同意(isInRequest): " + isInRequest);
			
			Boolean isWaitingToAccept = frs.isWaitingToAccept(memberNo, friendNo);
			System.out.println("是否等待你的同意(isWaitingToAccept): " + isWaitingToAccept);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/test/myFriendTest.jsp");
		rd.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
