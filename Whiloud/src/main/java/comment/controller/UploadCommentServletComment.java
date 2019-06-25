package comment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import comment.service.CommentService;
import comment.model.CommentBean;
import post.model.PostBean;
import post.service.PostService;
import register.model.MemberBean;
import register.service.MemberService;

@WebServlet("/UploadCommentServletJSON")
public class UploadCommentServletComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession(false);
		
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		Integer memberNo = LoginMemberBean.getMemberNo();
		Integer postNo = Integer.parseInt(request.getParameter("postNo"));
		String commentText =  request.getParameter("commentText");
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || "".equals(ip)) {
			ip = request.getRemoteAddr();
		}
		
		System.out.println("memberNo: " + memberNo + ", postNo: " + postNo + ", String: " + commentText);
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		MemberService ms = ctx.getBean(MemberService.class);
		
		PostService ps = ctx.getBean(PostService.class);
		CommentService cs = ctx.getBean(CommentService.class);
		
		MemberBean mb = ms.getMember("" + memberNo);
		PostBean pb = ps.getPostDetailBean("" + postNo);
		
		cs.insertComment(mb, pb, commentText, ip);
		
		//重新讀取全部的留言，並回傳給使用者
		cs.getCommentByPostNo(postNo);
		
		List<CommentBean> cbl = cs.getCommentByPostNo(postNo);
		
		//用鐵法煉鋼的方式排除stackoverflow
		for(CommentBean cb:cbl) {
			cb.setPb(null);
		}
		
		String jsonOut = new JSONArray(cbl).toString();
		System.out.println("轉換JSON成功");
		System.out.println("List to JSON: " + jsonOut);
		
		// 送回客戶端
		writeText(response, jsonOut);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
