package post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import clip.model.ClipBean;
import clip.model.ScriptBean;
import clip.service.ClipService;
import clip.service.ScriptService;
import clip.service.impl.ClipServiceImpl;
import clip.service.impl.ScriptServiceImpl;
import register.model.MemberBean;



@WebServlet("/EnterEditingPageServletV2")
public class EnterEditingPageServletV2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		Integer memberNo = LoginMemberBean.getMemberNo();
		String clipNo = request.getParameter("clipNo");
		String roleNo = request.getParameter("roleSelect");	//要配音的角色
		System.out.println("clipNo:" + clipNo + " memberNo:" + memberNo + " roleNo:" + roleNo);
		ServletContext sc = getServletContext();
		WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		ClipService cs = ctx.getBean(ClipService.class);
		ClipBean cb = cs.getClip(clipNo);
		ScriptService ss = ctx.getBean(ScriptService.class);
		List<ScriptBean> scriptBeanList = ss.getScriptList(clipNo);
		request.setAttribute("roleNo", roleNo);
		request.setAttribute("clipBean", cb);
		request.setAttribute("scriptBeanList", scriptBeanList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/player/rec.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
