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
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import post.model.PostBean;
import post.service.PostService;
import register.model.MemberBean;

/**
 * Servlet implementation class DeleteMyPostServlet
 */
@WebServlet("/DeleteMyPostServlet")
public class DeleteMyPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("進入DeleteMyPostServlet");
		
		
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		Integer memberNo = LoginMemberBean.getMemberNo();
		Integer postNo = Integer.parseInt(request.getParameter("postNo"));
		
		ServletContext sc = request.getServletContext();
		WebApplicationContext ctx  =WebApplicationContextUtils.getWebApplicationContext(sc);
		PostService ps = ctx.getBean(PostService.class);
		PostBean pb = ps.getPostDetailBean(""+postNo);
		
		if(pb.getMb1().getMemberNo() == memberNo) {
			ps.deletePost(pb);
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/EnterMyVideo");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
