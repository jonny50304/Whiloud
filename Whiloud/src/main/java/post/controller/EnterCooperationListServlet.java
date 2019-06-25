package post.controller;

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

import post.model.PostBean;
import post.model.RecordBean;
import post.service.PostService;
import post.service.RecordService;

/**
 * Servlet implementation class EnterCooperationListServlet
 */


@WebServlet("/EnterCooperationListServlet")
public class EnterCooperationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		try {
			ServletContext  sc = getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			PostService postservice = ctx.getBean(PostService.class);
			
			List<PostBean> cooperationList = postservice.getAllCooperation();
			System.out.println(cooperationList);
			request.setAttribute("cooperationList", cooperationList);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request
				.getRequestDispatcher("/cooperationList.jsp");
		// 請容器代為呼叫下一棒程式
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
