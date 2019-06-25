package register.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import init.GlobalService;
import register.model.MemberBean;
import register.service.MemberService;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/UpdateInformationServlet")
public class UpdateInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMessage = new HashMap<>();

		Map<String, String> okMessage = new HashMap<>();
		HttpSession session = request.getSession(false);
		request.setAttribute("errorMessage", errorMessage);
		session.setAttribute("okMessage", okMessage);

		String phone = null;
		Date birthday = null;
		String introduction = null;
		String interest = null;

		Collection<Part> parts = request.getParts();
		GlobalService.exploreParts(parts, request);

		if (parts != null) {
			for (Part p : parts) {
				System.out.println(p.getName());
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				if (p.getContentType() == null) {
					if (fldName.equals("phone")) {
						phone = value;
						System.out.println("get phone: " + phone);
					} else if (fldName.equals("birthday")) {
						// 進行轉換
						try {
							birthday = Date.valueOf(value);
							System.out.println("birthday:" + birthday);
						} catch (Exception e) {
							System.out.println("日期轉換出現錯誤，設birthday=null");
							birthday = null;
						}

					} else if (fldName.equals("introduction")) {
						introduction = value;
					} else if (fldName.equals("interest")) {
						String[] values = request.getParameterValues(fldName);
						interest = Arrays.toString(values).substring(1, Arrays.toString(values).length() - 1);
						System.out.println("interest:" + interest);
					}
				}
			}
		}else {
					errorMessage.put("errorTitle", "此表單不是上傳檔案的表單|");
		}
		if (!errorMessage.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("EnterSettingsServlet");
			rd.forward(request, response);

			return;
		}
		try {
			ServletContext sc = getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			MemberService service = ctx.getBean(MemberService.class);
			MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
			Integer memberNo = LoginMemberBean.getMemberNo();

			MemberBean mb = new MemberBean(memberNo, null, null, null, null, phone, null, null, birthday, 0,
					false, false, introduction, interest);
			int n = service.updateInformation(mb);

			System.out.println("資料更改memberNo=" + mb.getMemberNo());

			if (n == 1) {
				okMessage.put("UpdateOK", "會員資料修改成功");
				request.setAttribute("UpdateOK", "會員資料修改成功");
				response.sendRedirect("EnterSettingsServlet");
				return;
			} else {
				errorMessage.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)|");
			}

			if (!errorMessage.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("EnterSettingsServlet");
				rd.forward(request, response);
				return;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMessage.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
			RequestDispatcher rd = request.getRequestDispatcher("EnterSettingsServlet");
			rd.forward(request, response);
		}

	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
