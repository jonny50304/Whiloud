package chat.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import chat.model.ChatBean;
import chat.service.ChatService;
import chat.service.impl.ChatServiceImpl;


@WebServlet("/chat/GetChatServlet")
public class GetChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("進入getChatServlet");
		//System.out.println("memberNo1: " + memberNo1 +", memberNo2: " + memberNo2);
		try {
			
			request.setCharacterEncoding("UTF-8");
			String memberNo1 = request.getParameter("memberNo1");
			String memberNo2 = request.getParameter("memberNo2");
//			request.setAttribute("memberNo1",memberNo1 );
//			request.setAttribute("memberNo2", memberNo2);
			ServletContext sc = getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			ChatService service = ctx.getBean(ChatService.class);
			List<ChatBean> chatList = service.getChat(memberNo1, memberNo2);
			System.out.println("chatList讀取完畢: " + chatList);
			
			String jsonOut = new JSONArray(chatList).toString();
			System.out.println("轉換JSON成功");
			System.out.println("List to JSON: " + jsonOut);

			// 送回客戶端
			writeText(response, jsonOut);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("進入getChatServlet");
		doPost(req, resp);
	}
	
	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
