package register.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import init.SystemUtils2018;
import post.service.RecordService;
import register.model.MemberBean;
import register.service.MemberService;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024
		* 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/UpdatePictureServlet")
public class UpdatePictureServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		Integer memberNo = LoginMemberBean.getMemberNo();
		ServletContext sc = getServletContext();
		
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService service = ctx.getBean(MemberService.class);
		MemberBean mb = service.getMember("" + memberNo);
		
		Map<String, String> errorMessage = new HashMap<>();

		Map<String, String> okMessage = new HashMap<>();
		request.setAttribute("errorMessage", errorMessage);
		session.setAttribute("okMessage", okMessage);
		
		String fileName = null;

		Collection<Part> parts = request.getParts();


		try {
				// 開始處理大頭貼
				if (parts != null) {
					for (Part p : parts) {
						if (p.getContentType() != null) {
							System.out.println("p.getSubmittedFileName=" + p.getSubmittedFileName());
							String subFilename = p.getSubmittedFileName()
									.substring(p.getSubmittedFileName().lastIndexOf("."));
							fileName = "" + mb.getMemberNo() + subFilename;
							p.write(session.getServletContext().getRealPath("")+ "data/memberPicture/" +fileName);
							System.out.println("大頭貼 " + fileName + " 已儲存到server");
							service.savePicture(mb.getMemberNo(), "/data/memberPicture/" + fileName);
							System.out.println("大頭貼路徑已寫入資料庫");
							
							okMessage.put("UpdateOK", "會員資料修改成功");
							response.sendRedirect("EnterSettingsServlet");
							return;

						}
					}
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

}
