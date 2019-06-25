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

import clip.service.ClipService;
import clip.service.ScriptService;
import clip.service.impl.ClipServiceImpl;
import clip.service.impl.ScriptServiceImpl;
import post.model.PostBean;
import post.model.RecordBean;
import post.repository.PostDao;
import post.repository.impl.PostDaoImpl_Hibernate;
import post.service.PostService;
import post.service.RecordService;
import post.service.impl.PostServiceImpl;
import post.service.impl.RecordServiceImpl;
import register.service.MemberService;
import register.service.impl.MemberServiceImpl_Hibernate;

//@MultipartConfig(location = "/Applications/＿Ｗhiloud/Whiloud/src/main/webapp/data/record/", fileSizeThreshold = 5 * 1024
//		* 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@MultipartConfig(location = "../../../../wtpwebapps/Whiloud/data/record/", fileSizeThreshold = 5 * 1024
* 1024, maxFileSize = 1024 * 1024 * 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/post/PublishPostServlet")
public class PublishPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("進入PublishPostServlet");
		request.setCharacterEncoding("UTF-8");
		// Post Table欄位
		Integer postNo = null;
		String postTitle = null;
		Integer memberNo1 = 1;
		Integer memberNo2 = 1;
		Integer clipNo = null;
		Timestamp creationDateTime = new java.sql.Timestamp(System.currentTimeMillis());
		String text = null;
		Boolean friendOnly = false;
		Boolean done = null;
		Integer roleNo = null;
		Integer notDoneRoleNo = null;

		// 控制共要新增幾筆Record資料的變數
		Integer scriptStartNo = null;
		Integer scriptCount = null;

		// Record Table欄位
		Integer recordNo = null;
		// postNo與post欄位共用
		// memberNo與post欄位共用
		Integer scriptNo = null;
		String recordPath = null;
		// creationDateTime與post欄位共用
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		Collection<Part> parts = request.getParts();

		// 抓取新增post table所需要的資料
		try {
			if (parts != null) {
				for (Part p : parts) {
					String key = p.getName();
					String value = request.getParameter(key);
					if (p.getContentType() == null) {
						if (key.equals("postTitle")) {
							postTitle = value;
							System.out.println("get postTitle:" + postTitle);
						}
						if (key.equals("memberNo")) {
							memberNo1 = Integer.parseInt(value);
							System.out.println("get memberNo:" + memberNo1);
						}
						if (key.equals("text")) {
							text = value;
							System.out.println("get text:" + text);
						}
						if (key.equals("clipNo")) {
							clipNo = Integer.parseInt(value);
							System.out.println("get clipNo:" + clipNo);
						}
						if (key.equals("friendOnly")) {
							friendOnly = Boolean.parseBoolean(value);
							System.out.println("get friendOnly:" + friendOnly);
						}
						if (key.equals("done")) {
							done = Boolean.parseBoolean(value);
							System.out.println("get done:" + done);
						}
						if (key.equals("scriptCount")) {
							scriptCount = Integer.parseInt(value);
							System.out.println("get scriptCount:" + scriptCount);
						}
						if (key.equals("scriptStartNo")) {
							scriptStartNo = Integer.parseInt(value);
							System.out.println("get scriptStart:" + scriptStartNo);
						}
						if (key.equals("roleNo")) {
							roleNo = Integer.parseInt(value);
							System.out.println("get scriptStart:" + roleNo);

							if (roleNo == 1) {
								notDoneRoleNo = 2;
							} else if (roleNo == 2) {
								notDoneRoleNo = 1;
							} else {
								notDoneRoleNo = 0;
							}
						}
					}
					// 預設值：
				}

				// 開始對post Table進行資料新增
				
				PostService ps = ctx.getBean(PostService.class);
				PostBean postBean = new PostBean(postNo, postTitle, creationDateTime, text, friendOnly, done,
						notDoneRoleNo, false);
				postBean.setMb1(ctx.getBean(MemberService.class).getMember(String.valueOf(memberNo1)));
				postBean.setMb2(ctx.getBean(MemberService.class).getMember(String.valueOf(memberNo2)));
				postBean.setCb(ctx.getBean(ClipService.class).getClip(String.valueOf(clipNo)));
				ps.savePost(postBean);
				postNo = postBean.getPostNo();
				System.out.println("已新增PostNo = " + postNo);

			}
			System.out.println("post Done");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 已取得postNo新增時的主鍵
		if (postNo != null) {
			// 先新增總共需要的筆數
			
			RecordService rs =  ctx.getBean(RecordService.class);
			try {
				for (int i = scriptStartNo; i < scriptStartNo + scriptCount; i++) {

					RecordBean rb = new RecordBean(null, null, creationDateTime);
					rb.setMb(null);
					rb.setPb(ctx.getBean(PostService.class).getPostDetailBean(String.valueOf(postNo)));
					rb.setSb(ctx.getBean(ScriptService.class).getScript(String.valueOf(i)));

					rs.saveRecord(rb);
					// recordDAO.insertRecord(recordBean);
					System.out.println("以新增scriptNo = " + i);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 抓取update record table所需要的資料
			try {
				if (parts != null) {
					for (Part p : parts) {
						if (p.getContentType() != null) {
							scriptNo = Integer.parseInt(p.getName());
							String fileName = String.valueOf(postNo) + "_" + p.getSubmittedFileName();
//							p.write(fileName);
							HttpSession session = request.getSession(false);
							p.write(session.getServletContext().getRealPath("")+ "data/record/" +fileName);
							recordPath = "/data/record/" + fileName;
							rs.updateRecord(postNo, scriptNo, recordPath, memberNo1);

						}
					}
				}
				System.out.println("record Done");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
