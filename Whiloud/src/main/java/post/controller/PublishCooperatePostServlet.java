package post.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;

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

import post.service.PostService;
import post.service.RecordService;
import post.service.impl.PostServiceImpl;
import post.service.impl.RecordServiceImpl;


//@MultipartConfig(location = "/Applications/＿Ｗhiloud/Whiloud/src/main/webapp/data/record/", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
//@MultipartConfig(location = "../../../../wtpwebapps/Whiloud/data/record/", fileSizeThreshold = 5 * 1024
//* 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024
* 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/post/PublishCooperatePostServlet")
public class PublishCooperatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入PublishCooperatePostServlet");
		request.setCharacterEncoding("UTF-8");

		
		//Post Table欄位
		Integer postNo = null;
		Integer memberNo2 = 0;
		
		//Record Table欄位
		Integer scriptNo = null;
		String recordPath = null;
		Integer roleNo = null;
		//creationDateTime與post欄位共用
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		Collection<Part> parts = request.getParts();
		
		try {
			if(parts!=null) {
				for(Part p : parts) {
					String key = p.getName();
					String value = request.getParameter(key);
					if(p.getContentType()==null) {
						if(key.equals("memberNo")) {
							memberNo2 = Integer.parseInt(value);
							System.out.println("get memberNo:" + memberNo2);
						}
						if(key.equals("postNo")) {
							postNo = Integer.parseInt(value);
							System.out.println("get postNo:" + postNo);
						}
						if(key.equals("roleNo")) {
							roleNo = Integer.parseInt(value);
							System.out.println("get roleNo:" + roleNo);
						}
					}
				}

				PostService ps = ctx.getBean(PostService.class);
				ps.updatePostMemberNo2(postNo, memberNo2);
			}
			System.out.println("post Done");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(parts!=null) {
				for(Part p : parts) {
					if(p.getContentType()!=null) {
						scriptNo = Integer.parseInt(p.getName());
						String fileName = String.valueOf(postNo) + "_" + p.getSubmittedFileName();
						
						HttpSession session = request.getSession(false);
						p.write(session.getServletContext().getRealPath("")+ "data/record/" +fileName);
						
						recordPath = "/data/record/" + fileName;
						
						RecordService rs = ctx.getBean(RecordService.class);
						rs.updateNotDoneRecord(postNo, memberNo2, scriptNo, recordPath);
					}
				}
			}
			System.out.println("record Done");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
