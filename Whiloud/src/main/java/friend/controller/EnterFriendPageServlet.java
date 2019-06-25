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
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import friend.model.FriendBean;
import friend.service.FriendRequestService;
import friend.service.FriendService;
import like.model.LikePostBean;
import like.model.LikeRecordBean;
import like.service.LikePostService;
import like.service.LikeRecordService;
import post.model.PostBean;
import post.service.PostService;
import register.model.MemberBean;
import register.service.MemberService;

/**
 * Servlet implementation class EnterFriendPage
 */
@WebServlet("/EnterFriendPageServlet")
public class EnterFriendPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		
		
		String memberNo = request.getParameter("memberNo");
		ServletContext sc = request.getServletContext();
		WebApplicationContext ctx  =WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService ms = ctx.getBean(MemberService.class);
		FriendService fs = ctx.getBean(FriendService.class);
		PostService ps = ctx.getBean(PostService.class);
		LikePostService lps = ctx.getBean(LikePostService.class);
		LikeRecordService lrs = ctx.getBean(LikeRecordService.class);
		
		MemberBean mb = ms.getMember(memberNo);
		
		Boolean isFriend = fs.isFriend(LoginMemberBean.getMemberNo(), Integer.parseInt(memberNo));
		List<FriendBean> HisFriends = fs.getAllFriends(Integer.parseInt(memberNo));
		Boolean isInRequest = false;
		Boolean isWaitingYouToAccept = false;
		if(!isFriend) {
			FriendRequestService frs = ctx.getBean(FriendRequestService.class);
			isInRequest = 	frs.isInRequest(LoginMemberBean.getMemberNo(), Integer.parseInt(memberNo));
			System.out.println("你的交友邀請是否還在等待對方同意(isInRequest): " + isInRequest);
			
			isWaitingYouToAccept = frs.isWaitingToAccept(LoginMemberBean.getMemberNo(), Integer.parseInt(memberNo));
			System.out.println("是否等待你的同意(isWaitingToAccept): " + isWaitingYouToAccept);
		}
		
		List<PostBean> pbl = ps.getPostsByMemberNo(Integer.parseInt(memberNo));
		List<LikePostBean> lpbl = lps.getLikeCountByMemberNo(Integer.parseInt(memberNo));
		List<LikeRecordBean> lrb = lrs.getLikeCountByMemberNo(Integer.parseInt(memberNo));
		
		Integer likeCounts = lpbl.size();
		likeCounts += lrb.size();
				
		request.setAttribute("memberBean", mb);
		request.setAttribute("HisFriends", HisFriends);
		request.setAttribute("likeCounts", likeCounts);
		request.setAttribute("isFriend", isFriend);
		request.setAttribute("isInRequest", isInRequest);
		request.setAttribute("isWaitingYouToAccept", isWaitingYouToAccept);
		request.setAttribute("postBeanList", pbl);
		
		RequestDispatcher rd = request.getRequestDispatcher("/recommandFriend.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
