package register.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

import init.GlobalService;
import register.model.MemberBean;
import register.service.MemberService;


@WebServlet("/UpdatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdatePasswordServlet");
		Map<String, String> okMessage = new HashMap<>();
		
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		Integer memberNo = LoginMemberBean.getMemberNo();
		String password = request.getParameter("newPassword");
		System.out.println("收到的password: " + password);
		// 將密碼加密兩次，以便與存放在表格內的密碼比對
		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		System.out.println("加密的password: " + password);
		
		
		ServletContext sc  =getServletContext();
		WebApplicationContext ctx  =  WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService ms = ctx.getBean(MemberService.class);
		
		
		ms.updatePassword(memberNo, password);
		
		//-------------------------------
		//  密碼更改後，還沒寫清除cookies的部分
		//-------------------------------
		
		okMessage.put("UpdateOK", "會員資料修改成功");
		request.setAttribute("UpdateOK", "會員資料修改成功");
		response.sendRedirect("EnterSettingsServlet");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
