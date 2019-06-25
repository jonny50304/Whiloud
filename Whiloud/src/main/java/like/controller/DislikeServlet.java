package like.controller;

import java.io.IOException;

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

import like.service.LikePostService;
import like.service.LikeRecordService;
import post.model.PostBean;
import post.model.RecordBean;
import post.service.PostService;
import post.service.RecordService;
import register.model.MemberBean;
import register.service.MemberService;

/**
 * Servlet implementation class DislikeServlet
 */
@WebServlet("/DislikeServlet")
public class DislikeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//如果在轉換recordNo沒出錯，代表送來的查詢字串有recordNo
		System.out.println("------LikeServlet------");
		request.setCharacterEncoding("UTF-8");

		
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		Integer memberNo = LoginMemberBean.getMemberNo();
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService ms = ctx.getBean(MemberService.class);
		MemberBean mb = ms.getMember("" + memberNo);
		String recordNo = request.getParameter("recordNo");
		
		//如果在轉換recordNo沒出錯，代表送來的查詢字串有recordNo，反之就是按postNo讚
		if(recordNo!= null) {
			RecordService rs = ctx.getBean(RecordService.class);
			LikeRecordService lrs = ctx.getBean(LikeRecordService.class);
			RecordBean rb = rs.getRecordBean(Integer.valueOf(recordNo));
			lrs.dislikeRecord(mb, rb);
		}else {
			Integer postNo = Integer.parseInt(request.getParameter("postNo"));
			PostService ps = ctx.getBean(PostService.class);
			LikePostService lps = ctx.getBean(LikePostService.class);
			PostBean pb = ps.getPostDetailBean(""+ postNo);
			lps.dislikePost(mb, pb);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/GetPostDetailServletV2");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
