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
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import like.model.LikePostBean;
import like.model.LikeRecordBean;
import like.service.LikePostService;
import like.service.LikeRecordService;
import register.model.MemberBean;
import register.service.MemberService;


@WebServlet("/EnterSettingsServlet")
public class EnterSettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("EnterSettingsServlet");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		Integer memberNo = LoginMemberBean.getMemberNo();
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService ms = ctx.getBean(MemberService.class);
		LikeRecordService lrs = ctx.getBean(LikeRecordService.class);
		LikePostService lps = ctx.getBean(LikePostService.class);
		
		MemberBean mb = ms.getMember("" + memberNo);
		//List<LikePostBean> lpbl = lps.getLikeCountByMemberNo(memberNo);
		//List<LikeRecordBean> lrbl = lrs.getLikeCountByMemberNo(memberNo);
		
		List<LikePostBean> lpbl = lps.getLikeListByMemberNo(memberNo);
		List<LikeRecordBean> lrbl = lrs.getLikeListByMemberNo(memberNo);
		
		request.setAttribute("memberBean", mb);
		request.setAttribute("LikePostBeanList", lpbl);
		request.setAttribute("LikeRecordBeanList", lrbl);
		
		RequestDispatcher rd = request.getRequestDispatcher("/settings.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
