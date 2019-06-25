package post.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import clip.service.ClipService;
import clip.service.ScriptService;
import post.model.PostBean;
import post.model.RecordBean;
import post.service.PostService;
import post.service.RecordService;
import register.service.MemberService;


@WebServlet("/PublishPostServletAndorid")
public class PublishPostServletAndroid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		ServletContext sc = getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		System.out.println("進入PublishPostServlet");
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
		
		String memberNo = String.valueOf(jsonObject.get("memberNo").getAsInt());
		String clipNo  = String.valueOf(jsonObject.get("clipNo").getAsInt());
	
		//Post Table 欄位
		Integer postNo = null;
		String postTitle = jsonObject.get("postTitle").getAsString();
		Timestamp creationDateTime = new java.sql.Timestamp(System.currentTimeMillis());
		String postText =  jsonObject.get("postText").getAsString();
		Boolean friendOnly = false;
		Boolean done = true;
		Integer roleNo = null;
		Integer notDoneRoleNo = 0;
		// 開始對post Table進行資料新增
		PostService ps = ctx.getBean(PostService.class);
		PostBean postBean = new PostBean(postNo, postTitle, creationDateTime, postText, friendOnly, done,
				notDoneRoleNo, false);
		postBean.setMb1(ctx.getBean(MemberService.class).getMember(memberNo));
		postBean.setCb(ctx.getBean(ClipService.class).getClip(clipNo));
		ps.savePost(postBean);
		postNo = postBean.getPostNo();
		System.out.println("已經postNo:"+String.valueOf(postNo));
		
		// 控制共要新增幾筆Record資料的變數
		Integer scriptStartNo  = jsonObject.get("scriptStartNo").getAsInt();
		System.out.println(scriptStartNo);
		Integer scriptCount = jsonObject.get("scriptCount").getAsInt();
		// Record Table欄位
		Integer recordNo = null;
		// postNo與post欄位共用
		// memberNo與post欄位共用
		Integer scriptNo = null;
		String recordPath = null;
		//抓到影片的base64檔
//		for(int i = 1 ;i<=scriptCount;i++) {
//		
//		 recordPath  = jsonObject.get(memberNo+"_"+clipNo+"_"+String.valueOf(i)).getAsString();
//		    System.out.println(memberNo+"_"+clipNo+"_"+String.valueOf(i));
//			byte[] recordByte = Base64.getMimeDecoder().decode(recordPath.replaceAll("\\n","").replaceAll("\uFEFF",""));
//			File file = null;
//			String filePath= "/Users/chian0418/Downloads/_JSP_JDBC/tomcat9/webapps/Whiloud/data/record/";
//			String fileName =String.valueOf(postNo)+"_"+i+".wav";
//			file  =new File(filePath,fileName);
//			try(FileOutputStream fos = new FileOutputStream(file,true)){
//				fos.write(recordByte);
//			}catch(Exception e) {
//				e.printStackTrace();
//			}
//		
//
//		}
		// 已取得postNo新增時的主鍵
		if(postNo!=null) {
			String postOK  ;
			// 先新增總共需要的筆數
			RecordService rs =  ctx.getBean(RecordService.class);
			try {
				for(int i = scriptStartNo ;i<scriptStartNo+scriptCount;i++) {
					System.out.println(i);
					 recordPath  = jsonObject.get(memberNo+"_"+clipNo+"_"+String.valueOf(i)).getAsString();
					    System.out.println(memberNo+"_"+clipNo+"_"+String.valueOf(i));
						byte[] recordByte = Base64.getMimeDecoder().decode(recordPath.replaceAll("\\n","").replaceAll("\uFEFF",""));
						File file = null;
						//String filePath= "/Users/chian0418/Downloads/_JSP_JDBC/tomcat9/webapps/Whiloud/data/record/";
						String filePath = "C:\\_JSP\\eclipse-workspaceJSP\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Whiloud\\data\\record";
						String fileName =String.valueOf(postNo)+"_"+i+".wav";
						file  =new File(filePath,fileName);
						try(FileOutputStream fos = new FileOutputStream(file,true)){
							fos.write(recordByte);
						}catch(Exception e) {
							e.printStackTrace();
						}
					String servletRecordPath  = "/data/record/"+fileName;
					RecordBean rb = new RecordBean(null, servletRecordPath, creationDateTime);
					rb.setMb(ctx.getBean(MemberService.class).getMember(memberNo));
					rb.setPb(ctx.getBean(PostService.class).getPostDetailBean(String.valueOf(postNo)));
					rb.setSb(ctx.getBean(ScriptService.class).getScript(String.valueOf(i)));
					rs.saveRecord(rb);
				}
				postOK= "postOK";
				JsonObject	jsonObjectResp = new JsonObject();
				jsonObjectResp.addProperty("postOK", postOK);
				writeText(response,gson.toJson(jsonObjectResp));

				
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}


	}
	
	
	
	 @SuppressWarnings("unused")
	private void writeText(HttpServletResponse response, String outText) throws IOException {
			response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.print(outText);
			System.out.println("output: " + outText);
		}

}
