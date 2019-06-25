package post.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import clip.model.ClipBean;
import clip.service.ClipService;
import friend.model.FriendBean;
import friend.service.FriendService;
import post.model.PostBean;
import post.service.PostService;
import register.model.MemberBean;
import register.service.MemberService;

/**
 * Servlet implementation class EnterPostListServlet
 */
@WebServlet("/EnterPostListServlet")
public class EnterPostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入Filter02EnterMain");
		
		//判斷是否已經登入，如果沒登入，就以訪客方式拜訪
		HttpSession session = request.getSession(false);
		if(session==null) {
			
			System.out.println("即將以訪客身分登入");
			//---------------------
			//    TODO:訪客登入
			//---------------------
		}
		
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		
		request.setCharacterEncoding("UTF-8");
		ServletContext sc = request.getServletContext();
		WebApplicationContext ctx  =WebApplicationContextUtils.getWebApplicationContext(sc);
		PostService ps = ctx.getBean(PostService.class);

		
		
		List<PostBean> postBeanList = ps.getAllDonePosts();

		
		
		request.setAttribute("postBeanList", postBeanList);

		RequestDispatcher rd = request.getRequestDispatcher("/postList.jsp");
		// 請容器代為呼叫下一棒程式
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
