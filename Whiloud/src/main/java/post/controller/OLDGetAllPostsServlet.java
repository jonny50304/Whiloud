package post.controller;

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

import post.model.PostBean;
import post.repository.impl.PostDaoImpl_Hibernate;
import post.service.PostService;
import post.service.impl.PostServiceImpl;

@WebServlet("/post/GetAllPosts")
public class OLDGetAllPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		try {
			ServletContext sc = getServletContext();
			WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
			
			PostService service = ctx.getBean(PostService.class);
		
			 List<PostBean> postList = service.getAllPosts();
			request.setAttribute("postList", postList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/post/getAllPosts.jsp");
			// 請容器代為呼叫下一棒程式
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
