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

import clip.model.ClipBean;
import clip.model.ScriptBean;
import clip.service.ClipService;
import clip.service.ScriptService;
import clip.service.impl.ClipServiceImpl;
import clip.service.impl.ScriptServiceImpl;



@WebServlet("/post/EnterSemiDonePageServlet")
public class OLDEnterSemiDonePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");
		Integer postNo = Integer.parseInt(request.getParameter("postNo"));
		String clipNo = request.getParameter("clipNo");
		Integer memberNo2 = Integer.parseInt(request.getParameter("memberNo2"));
		Integer notDoneRoleNo = Integer.parseInt(request.getParameter("notDoneRoleNo"));
		String cooperate = "true";
		
		System.out.println("postNo:" + postNo + " clipNo:" + clipNo + " memberNo2:" + memberNo2 +" notDoneRoleNo:" + notDoneRoleNo);
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		ClipService cs = ctx.getBean(ClipService.class);
		ClipBean cb = cs.getClip(clipNo);
		
		ScriptService ss = ctx.getBean(ScriptService.class);
		List<ScriptBean> scriptBeanList = ss.getScriptList(clipNo);
		request.setAttribute("postNo", postNo);
		request.setAttribute("cooperate", cooperate);
		request.setAttribute("roleNo", notDoneRoleNo);
		request.setAttribute("clipBean", cb);
		request.setAttribute("scriptBeanList", scriptBeanList);
		
		RequestDispatcher rd = request.getRequestDispatcher("editingPage.jsp");
		// 請容器代為呼叫下一棒程式
		rd.forward(request, response);
		//		try {
//			ClipDAO cd = new ClipDAOImpl();
//			ClipBean cb = cd.getClip(clipNo);
//			request.setAttribute("clipBean", cb);
//			
//			ScriptDAO sd = new ScriptDAOImpl();
//			LinkedList<ScriptBean> scriptBeanList = sd.getScript(clipNo);
//			request.setAttribute("scriptBeanList", scriptBeanList);
//			
//			request.setAttribute("postNo", postNo);
//			request.setAttribute("roleNo", notDoneRoleNo);
//			request.setAttribute("cooperate", cooperate);
//			
//			RequestDispatcher rd = request.getRequestDispatcher("editingPage.jsp");
//			// 請容器代為呼叫下一棒程式
//			rd.forward(request, response);
//			return;
//		} catch (Exception e) {
//			e.printStackTrace();
////			RequestDispatcher rd = request
////				.getRequestDispatcher("/ch04_02/InsertMemberError.jsp");
////			rd.forward(request, response);
//			return;
//		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
