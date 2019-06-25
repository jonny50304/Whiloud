package chat.controller;

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
import friend.service.FriendService;
import register.model.MemberBean;
import register.service.MemberService;

@WebServlet("/chat/EnterChatRoomServlet")
public class OLDEnterChatRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberNo = request.getParameter("memberNo");

		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService ms = ctx.getBean(MemberService.class);
		FriendService fs = ctx.getBean(FriendService.class); 
		MemberBean memberBean = ms.getMember(memberNo);
		List<FriendBean> friendBeanList = fs.getAllFriends(Integer.parseInt(memberNo));
		
		request.setAttribute("memberBean", memberBean);
		request.setAttribute("friendBeanList", friendBeanList);
		RequestDispatcher rd = request.getRequestDispatcher("/test/chatRoom.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
