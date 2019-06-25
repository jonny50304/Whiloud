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

/**
 * Servlet implementation class GetClipServletV2
 */
@WebServlet("/GetClipServletV2")
public class GetClipServletV2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetClipServletV2");

		
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
			
			RequestDispatcher rd = request.getRequestDispatcher("/player/getClip.jsp");
			// 請容器代為呼叫下一棒程式
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();

			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
