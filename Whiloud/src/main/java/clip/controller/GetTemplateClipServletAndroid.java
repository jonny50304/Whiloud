package clip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
import clip.model.ScriptBean;
import clip.service.ClipService;
import clip.service.ScriptService;
import clip.service.impl.ClipServiceImpl;
import clip.service.impl.ScriptServiceImpl;

@WebServlet("/clip/GetTemplateClipAndroid")
public class GetTemplateClipServletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("進入: /clip/GetTemplateClipAndroid");
		// 進入本servlet不用接收任何action，Client端直接送出帶查詢字串的URI即可
		// URI請求method: 要用"GET" 
		// URI送出樣式: http://localhost:8080/Whiloud/clip/GetTemplateClipAndroid?clipNo=3
		// Server直接回傳1個JSON陣列List<ScriptBean>
		// 本程式不用任何GSON(想用好像也用不到)
		request.setCharacterEncoding("UTF-8");
		String clipNo = request.getParameter("clipNo");

		// 從資料庫抓取clip和script，
//		ClipService clipService = new ClipServiceImpl();
//		ClipBean clipBean = clipService.getClip(clipNo);
		ServletContext  sc  = getServletContext();
		WebApplicationContext ctx  = WebApplicationContextUtils.getWebApplicationContext(sc);
		
		ScriptService scriptservice = ctx.getBean(ScriptService.class);
		List<ScriptBean> scriptBeanList = scriptservice.getScriptList(clipNo);

		// Gson gson = new Gson();
		// jsonOut = gson.toJson(clipBeanList);
		//List<Object> listOut = Arrays.asList(clipBean, scriptBeanList);
		String jsonOut = new JSONArray(scriptBeanList).toString();
		System.out.println("List to JSON: " + jsonOut);

		// 送回客戶端
		writeText(response, jsonOut);

		// Android要有ScriptBean才能用GSON的typeToken來解讀
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		System.out.println("output: " + outText);
	}
}
