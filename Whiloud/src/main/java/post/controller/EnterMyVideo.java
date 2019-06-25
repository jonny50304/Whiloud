package post.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
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


@WebServlet("/EnterMyVideo")
public class EnterMyVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("進入EnterMyVideo");
		
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		
		request.setCharacterEncoding("UTF-8");
		ServletContext sc = request.getServletContext();
		WebApplicationContext ctx  =WebApplicationContextUtils.getWebApplicationContext(sc);
		PostService ps = ctx.getBean(PostService.class);
		
		List<PostBean> postBeanList = ps.getPostsByMemberNo(LoginMemberBean.getMemberNo());
		
		
		
		request.setAttribute("postBeanList", postBeanList);

		RequestDispatcher rd = request.getRequestDispatcher("/myVideo.jsp");
		// 請容器代為呼叫下一棒程式
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
