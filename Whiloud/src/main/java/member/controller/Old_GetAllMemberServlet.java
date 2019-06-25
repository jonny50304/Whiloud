package member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import register.model.MemberBean;
import register.repository.MemberDao;
import register.repository.impl.MemberDaoImpl_Hibernate;
import register.service.MemberService;
import register.service.impl.MemberServiceImpl_Hibernate;

/**
 * Servlet implementation class SelectAllMemberServlet
 */
@WebServlet("/member/GetAllMemberServlet")
public class Old_GetAllMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MemberBean> memberList = null;
		request.setCharacterEncoding("UTF-8");
		
		try {
			ServletContext sc = getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			
			MemberService service = ctx.getBean(MemberService.class);
			//System.out.println("memberDAO建立成功");
			memberList = service.getAllMember();
			//System.out.println("selectAll方法成功");
			request.setAttribute("memberList", memberList);
			//System.out.println("識別字memberMap設置成功");
			RequestDispatcher rd = request
					.getRequestDispatcher("/member/getMember.jsp");
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
