package clip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import clip.model.ClipBean;
import clip.repository.impl.ClipDaoImpl_Hibernate;
import clip.service.ClipService;
import clip.service.impl.ClipServiceImpl;
import post.model.PostBean;
import post.service.PostService;
import post.service.impl.PostServiceImpl;

@WebServlet("/clip/GetAllClipsServletAndroid")
public class GetAllClipsServletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("進入: /clip/GetAllPostsServletAndroid");
		//進入本servlet不用接收任何action，Client端直接送出URI即可
		//Server直接回傳JSON陣列(List<ClipBean>)，每個value是一個ClipBean
		//本程式不用任何GSON(想用好像也用不到)
		request.setCharacterEncoding("UTF-8");
		
		//從資料庫抓取全部的clip
		ServletContext sc = getServletContext();
		WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		ClipService cs  =  ctx.getBean(ClipService.class);
		List<ClipBean> clipBeanList = cs.getAllClips();
		
		//Gson gson = new Gson();
		//jsonOut = gson.toJson(clipBeanList);
		String jsonOut = new JSONArray(clipBeanList).toString();
		System.out.println("List to JSON: " + jsonOut);
		
		//送回客戶端
		writeText(response,jsonOut);
		
		//Android要有clipBean才能用GSON的typeToken來解讀
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
