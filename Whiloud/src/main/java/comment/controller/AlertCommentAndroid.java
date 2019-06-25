package comment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import comment.model.CommentBean;
import comment.service.CommentService;
import post.model.PostBean;
import post.service.PostService;
import register.model.MemberBean;
import register.service.MemberService;
@WebServlet("/AlertCommentAndroid")
public class AlertCommentAndroid extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		System.out.println("進入CooperatePostServletAndroid");
		String successMsg ="success";
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		BufferedReader br = request.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonIn.append(line);
		}
		System.out.println("input: " + jsonIn);
		JsonObject jsonObject = gson.fromJson(jsonIn.toString(),
				JsonObject.class);
		Integer memberNo  = jsonObject.get("memberNo").getAsInt();
		Integer postNo  = jsonObject.get("postNo").getAsInt();
		String comment  = jsonObject.get("comment").getAsString();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());

		MemberService  ms  = ctx.getBean(MemberService.class);
		PostService  ps =  ctx.getBean(PostService.class);
		CommentService cs = ctx.getBean(CommentService.class);
		MemberBean mb = ms.getMember(String.valueOf(memberNo));
		PostBean pb  = ps.getPostDetailBean(String.valueOf(postNo));
		
		cs.insertComment(mb, pb, comment, null);
		
		JsonObject message  = new JsonObject();
		
		message.addProperty("AlertOk", "Ok");
		
			writeText(response,message.toString());
		
		
	}
	 @SuppressWarnings("unused")
		private void writeText(HttpServletResponse response, String outText) throws IOException {
				response.setContentType(CONTENT_TYPE);
				PrintWriter out = response.getWriter();
				out.print(outText);
				System.out.println("output: " + outText);
			}
}
