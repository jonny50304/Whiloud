package clip.controller;

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

import clip.model.ClipBean;
import clip.model.ScriptBean;
import clip.service.ClipService;
import clip.service.ScriptService;
import clip.service.impl.ClipServiceImpl;
import clip.service.impl.ScriptServiceImpl;

@WebServlet("/clip/GetTemplateClip")
public class OLDGetTemplateClipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String clipNo = request.getParameter("clipNo");
		
		try {
			ServletContext sc = getServletContext();
			WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
			
			ClipService clipservice  =  ctx.getBean(ClipService.class);
	
			
			ClipBean cb = clipservice.getClip(clipNo);
			request.setAttribute("clipBean", cb);
			ScriptService scriptservice  = ctx.getBean(ScriptService.class);
			
			List<ScriptBean> scriptBeanList = scriptservice.getScriptList(clipNo);
			request.setAttribute("scriptBeanList", scriptBeanList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/clip/getClip.jsp");
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
