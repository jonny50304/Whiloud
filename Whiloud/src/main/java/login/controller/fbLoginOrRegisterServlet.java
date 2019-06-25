package login.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import login.service.LoginService;
import register.model.MemberBean;
import register.service.MemberService;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024
* 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/fbLoginOrRegisterServlet")
public class fbLoginOrRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMessage = new HashMap<>();
		Map<String, String> okMessage = new HashMap<>();
		
		HttpSession session = request.getSession(false);
		
		String account = null;
		String nickname = null;
		String gender = null;
		
		Collection<Part> parts = request.getParts();
		GlobalService.exploreParts(parts, request);
		
				
		if (parts != null) {
			for (Part p : parts) {
				System.out.println(p.getName());
				String fldName = p.getName();
				String value = request.getParameter(fldName);

				if (p.getContentType() == null) {
					if (fldName.equals("account")) {
						account = value;
					}else if(fldName.equals("nickname")) {
						nickname = value;
					}else if(fldName.equals("gender")) {
						gender = value;
						if (gender.equals("male")){
							gender = "M";
						}else if(gender.equals("female")) {
							gender = "F";
						}else{
							gender = "O";
						}
					}
				}
			}
		}
		
		ServletContext sc  =getServletContext();
		WebApplicationContext ctx  =  WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService ms = ctx.getBean(MemberService.class);
		LoginService loginService =ctx.getBean(LoginService.class);
		MemberBean mb = loginService.checkFbId(account);
		
		if (mb != null) {
			System.out.println("FB登入成功");
			session.setAttribute("LoginOK", mb);
			
		} else {
			System.out.println("執行FB註冊");
			
			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			// Blob blob= SystemUtils2018.fileToBlob(is, sizeInBytes);

			MemberBean mbRegister = new MemberBean(null, account, "", nickname, gender, null, ts,
					"/data/memberPicture/default.png", null, 0, true, false, null, null);
			int n = ms.saveMember(mbRegister);

			System.out.println("新增memberNo=" + mbRegister.getMemberNo());
			
			if (parts != null) {
				for (Part p : parts) {
					if (p.getContentType() != null) {
						String fileName = "" + mbRegister.getMemberNo() + ".jpg";
						p.write(session.getServletContext().getRealPath("")+ "data/memberPicture/" +fileName);
						System.out.println("大頭貼 " + fileName + " 已儲存到server");
						ms.savePicture(mbRegister.getMemberNo(), "/data/memberPicture/" + fileName);
						System.out.println("大頭貼路徑已寫入資料庫");
						mbRegister.setPicturePath("/data/memberPicture/" + fileName);
						session.setAttribute("LoginOK", mbRegister);

					}
				}
			}

		}	
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	public boolean getBineryResource(String surl ,String targetFolder, String encoding) {
		try {
			URL url = new URL(surl);;
			URLConnection con = url.openConnection();
			HttpURLConnection hcon= (HttpURLConnection) con;
			hcon.setDoInput(true);
			hcon.setDoOutput(false);
			
			//hcon.setRequestProperty("Referer", "https://tw.manhuagui.com/comic/17332/401677.html");
			hcon.setRequestProperty("Accept-Charset", encoding);
			hcon.setRequestProperty("Content-Type", "image/jpeg");
			hcon.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2.3) Gecko/20100401");
			hcon.setRequestMethod("GET");
			
			File dir = new File(targetFolder);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			String fileName = "test.jpg";
			File file = new File(targetFolder,fileName);
			
			try(InputStream is = hcon.getInputStream();
				FileOutputStream fos = new FileOutputStream(file);){
				byte[] b = new byte[8192];
				int len = 0;
				while((len = is.read(b))!=-1) {
					fos.write(b,0,len);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}