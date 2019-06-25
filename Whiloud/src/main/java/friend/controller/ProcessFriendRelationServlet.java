package friend.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import friend.service.FriendRequestService;
import friend.service.FriendService;
import register.model.MemberBean;
import register.service.MemberService;

/**
 * Servlet implementation class ProcessFriendRelationServlet
 */
@WebServlet("/ProcessFriendRelationServlet")
public class ProcessFriendRelationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ProcessFriendRelationServlet");
		
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		Integer memberNo = LoginMemberBean.getMemberNo();
		Integer friendNo = Integer.parseInt(request.getParameter("friendNo"));
		String action = request.getParameter("action");
		
		System.out.println("memberNo: " + memberNo + ", friendNo: " + friendNo + ", action: " + action);
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
		FriendService fs = ctx.getBean(FriendService.class);
		FriendRequestService frs = ctx.getBean(FriendRequestService.class);
		MemberService ms = ctx.getBean(MemberService.class);
		MemberBean mb1 = ms.getMember(String.valueOf(memberNo));
		MemberBean mb2 = ms.getMember(String.valueOf(friendNo));
		
		switch (action){
			case "sendRequest":
				frs.sendRequest(mb1, mb2);
				System.out.println("sendRequest done");
				break;
			case "cancelRequest":
				frs.cancelRequest(mb1,mb2);
				System.out.println("cancelRequest done");
				break;
			case "rejectRequest":
				frs.rejectRequest(mb1,mb2);
				System.out.println("rejectRequest done");
				break;
			case "cancelFriend":
				fs.cancelFriend(mb1,mb2);
				System.out.println("cancelFriend done");
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
