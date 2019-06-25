package like.controller;

import java.io.IOException;
import java.io.PrintWriter;

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


@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//如果在轉換postNo沒出錯，代表送來的查詢字串是postNo
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
		
		if(recordNo!= null) {
			RecordService rs = ctx.getBean(RecordService.class);
			LikeRecordService lps = ctx.getBean(LikeRecordService.class);
			RecordBean rb = rs.getRecordBean(Integer.valueOf(recordNo));
			lps.likeRecord(mb, rb);
		}else {
			Integer postNo = Integer.parseInt(request.getParameter("postNo"));
			PostService ps = ctx.getBean(PostService.class);
			LikePostService lps = ctx.getBean(LikePostService.class);
			PostBean pb = ps.getPostDetailBean(""+ postNo);
			lps.likePost(mb, pb);
		}

		
		RequestDispatcher rd = request.getRequestDispatcher("/GetPostDetailServletV2");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
