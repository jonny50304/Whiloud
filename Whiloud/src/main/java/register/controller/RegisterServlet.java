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
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z]).{8,})";
	private Pattern pattern = null;
	private Matcher matcher = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMessage = new HashMap<>();

		Map<String, String> okMessage = new HashMap<>();
		HttpSession session = request.getSession();
		request.setAttribute("errorMessage", errorMessage);
		session.setAttribute("okMessage", okMessage);
		String account = null;
		String password = null;
		String passwordCheck = null;
		String nickname = null;
		String gender = null;
		String phone = null;
		Date birthday = null;
		String introduction = null;
		String interest = null;
		String fileName = null;
		// long sizeInBytes = 0;
		// InputStream is =null;
		Collection<Part> parts = request.getParts();
		//GlobalService.exploreParts(parts, request);
		if (parts != null) {
			for (Part p : parts) {
				System.out.println(p.getName());
				String fldName = p.getName();
				String value = request.getParameter(fldName);

				if (p.getContentType() == null) {
					if (fldName.equals("account")) {
						account = value;
					} else if (fldName.equals("password")) {
						password = value;
					} else if (fldName.equals("passwordCheck")) {
						passwordCheck = value;
					} else if (fldName.equals("nickname")) {
						nickname = value;
					} else if (fldName.equals("gender")) {
						gender = value;
						request.setAttribute("gender", value);
					} else if (fldName.equals("phone")) {
						phone = value;
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
						// System.out.println("interest:" + Arrays.toString(values));
						interest = Arrays.toString(values).substring(1, Arrays.toString(values).length() - 1);
						System.out.println("interest:" + interest);
					}
				}

			}

			if (account == null || account.trim().length() == 0) {
				errorMessage.put("errorAccountEmpty", "帳號欄必須輸入|");
			}
			if (password == null || password.trim().length() == 0) {
				errorMessage.put("errorPasswordEmpty", "密碼欄必須輸入|");
			}
			if (passwordCheck == null || passwordCheck.trim().length() == 0) {
				errorMessage.put("errorPasswordCheckEmpty", "密碼確認欄必須輸入|");

			}
			if (password.trim().length() > 0 && passwordCheck.trim().length() > 0) {
				if (!password.trim().equals(passwordCheck.trim())) {
					errorMessage.put("errorPasswordCheckEmpty", "密碼欄必須與確認欄一致|");
					errorMessage.put("errorPasswordEmpty", "*");
				}
			}
			if (gender == null || gender.trim().length() == 0) {
				errorMessage.put("errorGenderEmpty", "性別欄必須輸入|");
			}

			if (nickname == null || nickname.trim().length() == 0) {
				errorMessage.put("errorNicknameEmpty", "暱稱欄必須輸入|");
			}
//		 			if(phone==null||phone.trim().length()==0) {
//		 				//errorMessage.put("errorPhoneEmpty","電話欄必須輸入");
//		 			}

		} else {
			errorMessage.put("errorTitle", "此表單不是上傳檔案的表單|");
		}
		if (errorMessage.isEmpty()) {
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(password);
			if (!matcher.matches()) {
				errorMessage.put("passwordError", "密碼要數字與英文字母組合|");
			}
		}
		if (!errorMessage.isEmpty()) {
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("joinus.jsp");
			rd.forward(request, response);

			return;
		}
		try {
			ServletContext sc = getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			MemberService service = ctx.getBean(MemberService.class);

			if (service.idExists(account)) {
				errorMessage.put("errorAccountExists", "帳號已存在,請重新輸入|");
			} else if (service.nicknameExists(nickname)) {
				errorMessage.put("errorNicknameExists", "暱稱已存在,請重新輸入|");
			} else {
				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
				// Blob blob= SystemUtils2018.fileToBlob(is, sizeInBytes);
				password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));

				MemberBean mb = new MemberBean(null, account, password, nickname, gender, phone, ts,
						"/data/memberPicture/default.png", birthday, 0, false, false, introduction, interest);
				int n = service.saveMember(mb);

				System.out.println("新增memberNo=" + mb.getMemberNo());

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

						}
					}
				}

				if (n == 1) {
					okMessage.put("InsertOK", "會員加入成功成功，請重新登入以開始使用本系統");
					response.sendRedirect("index.jsp");
					return;
				} else {
					errorMessage.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)|");
				}

			}
			if (!errorMessage.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("joinus.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMessage.put("errorIDDup", "新增此筆資料有誤(RegisterServlet)");
			RequestDispatcher rd = request.getRequestDispatcher("joinus.jsp");
			rd.forward(request, response);
		}
	}

}
