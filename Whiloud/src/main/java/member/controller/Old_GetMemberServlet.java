package member.controller;

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

import member.model.Old_FriendListBean;
import member.service.OldFriendService;
import member.service.impl.FriendService_Hibernate;
import register.model.MemberBean;
import register.service.MemberService;
import register.service.impl.MemberServiceImpl_Hibernate;

/**
 * Servlet implementation class ProcessMemberServlet
 */
@WebServlet("/member/GetMemberServlet")
public class Old_GetMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String memberNo = request.getParameter("memberNo");


		try {
			ServletContext sc = getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			
			MemberService service = ctx.getBean(MemberService.class);
			MemberBean mb = service.getMember(memberNo);
			request.setAttribute("member", mb);
			
			OldFriendService friendService =ctx.getBean(OldFriendService.class);
			List<Old_FriendListBean> friendList = friendService.getAllFriendList(memberNo);
			request.setAttribute("friendList", friendList);
			List<Old_FriendListBean> friendRequest = friendService.getAllFriendRequest(memberNo);
			request.setAttribute("friendRequest", friendRequest);
			List<Old_FriendListBean> friendConfirm = friendService.getAllFriendConfirm(memberNo);
			request.setAttribute("friendConfirm", friendConfirm);
			
			RequestDispatcher rd = request
					.getRequestDispatcher("/member/getMember.jsp");
			// 請容器代為呼叫下一棒程式
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
//			RequestDispatcher rd = request
//				.getRequestDispatcher("/ch04_02/InsertMemberError.jsp");
//			rd.forward(request, response);
			return;
		}
	}

}
