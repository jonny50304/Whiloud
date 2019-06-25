package friend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import friend.model.FriendBean;
import friend.model.FriendRequestBean;
import friend.service.FriendRequestService;
import friend.service.FriendService;
import friend.service.impl.FriendRequestServiceImpl;
import friend.service.impl.FriendServiceImpl;

/**
 * Servlet implementation class CheckFriendServlet
 */
@WebServlet("/friend/CheckFriendServlet")
public class OLDCheckFriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OLDCheckFriendServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入CheckFriendServlet");
		request.setCharacterEncoding("UTF-8");
		Integer memberNo = Integer.parseInt(request.getParameter("memberNo"));
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		FriendService fs = ctx.getBean(FriendService.class);
		List<FriendBean> friendBeanList = fs.getAllFriends(memberNo);
		System.out.println("friendBeanList: " + friendBeanList);
		
		FriendRequestService frs = ctx.getBean(FriendRequestService.class);
		List<FriendRequestBean> iInvitList = frs.getAllInvite(memberNo);
		System.out.println("iInvitList: " + iInvitList);
		List<FriendRequestBean>  iWasInvitedList = frs.getAllWasInvited(memberNo);
		System.out.println("iWasInvitedList: " + iWasInvitedList);
		
		request.setAttribute("friendBeanList",friendBeanList);
		request.setAttribute("iInvitList",iInvitList);
		request.setAttribute("iWasInvitedList",iWasInvitedList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/test/myFriendTest.jsp");
		rd.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
