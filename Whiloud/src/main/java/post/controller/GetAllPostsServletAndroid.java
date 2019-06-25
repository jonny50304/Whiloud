package post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import clip.service.impl.ClipServiceImpl;
import post.model.PostBean;
import post.service.PostService;
import post.service.impl.PostServiceImpl;

@WebServlet("/post/GetAllPostsServletAndroid")
public class GetAllPostsServletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入: /post/GetAllPostsServletAndroid");
		//進入本servlet不用接收任何action，Client端直接送出URI即可
		//Server直接回傳JSON陣列(List<PostBean>)，每個value是一個postBean
		//本程式不用任何GSON(想用好像也用不到)
		request.setCharacterEncoding("UTF-8");
		
		//從資料庫抓取全部的post
		ServletContext sc = getServletContext();
		WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		PostService ps = ctx.getBean(PostService.class);
		
		List<PostBean> postBeanList = ps.getAllPosts();
		for(PostBean pb:postBeanList) {
			pb.setLikePostBean(null);
		}
//		String jsonOut;
//		Gson gson = new Gson();
//		jsonOut = gson.toJson(postBeanList);
		String jsonOut = new JSONArray(postBeanList).toString();
		System.out.println("List to JSON: " + jsonOut);
		
		//送回客戶端
		writeText(response,jsonOut);
		
		//Android要有postBean才能用GSON的typeToken來解讀
	}

    private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
