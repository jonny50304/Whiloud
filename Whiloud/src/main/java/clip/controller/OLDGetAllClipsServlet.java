package clip.controller;

import java.io.IOException;
import java.util.LinkedList;
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
import clip.repository.impl.ClipDaoImpl_Hibernate;
import clip.service.ClipService;
import clip.service.impl.ClipServiceImpl;


@WebServlet("/clip/GetAllClips")
public class OLDGetAllClipsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		try {
			ServletContext sc = getServletContext();
			WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
			
			ClipService service  =  ctx.getBean(ClipService.class);
			
			List<ClipBean> clipList = service.getAllClips();
			request.setAttribute("clipList", clipList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/clip/getAllClips.jsp");
			// 請容器代為呼叫下一棒程式
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
