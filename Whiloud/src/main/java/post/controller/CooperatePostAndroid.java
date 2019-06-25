package post.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

import clip.model.ScriptBean;
import clip.service.ScriptService;
import post.model.PostBean;
import post.model.RecordBean;
import post.service.PostService;
import post.service.RecordService;
import register.model.MemberBean;
import register.service.MemberService;

@WebServlet("/post/CooperatePostServletAndroid")
public class CooperatePostAndroid extends HttpServlet{
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
		Integer postNo =  jsonObject.get("postNo").getAsInt();
		Integer memberNo = jsonObject.get("memberNo").getAsInt();
		Integer roleSelect = jsonObject.get("roleSelect").getAsInt();
		String clipNo  = String.valueOf(jsonObject.get("clipNo").getAsInt());
		Timestamp creationDateTime = new java.sql.Timestamp(System.currentTimeMillis());

		System.out.println("角色編號"+roleSelect);
		System.out.println("影片編號"+clipNo);
		System.out.println("文章編號:"+postNo);
		//開始對post進行修改
		PostService  ps  = ctx.getBean(PostService.class);



		ps.updatePostMemberNo2(postNo,memberNo);

		// 控制共要新增幾筆Record資料的變數
		Integer scriptStartNo  = jsonObject.get("scriptStartNo").getAsInt();
		System.out.println("台詞開始"+scriptStartNo);
		Integer scriptCount = jsonObject.get("scriptCount").getAsInt();
		System.out.println("台詞總數"+scriptCount);

		Integer recordNo = null;
		// postNo與post欄位共用
		// memberNo與post欄位共用
		Integer scriptNo = null;
		String recordPath = null;
		//取得塞選過的List<ScriptBean>
		ScriptService scriptservice = ctx.getBean(ScriptService.class);
		List<ScriptBean> scriptBeanList = scriptservice.getScriptList(clipNo);
		List<ScriptBean> scriptBeanSelect = new ArrayList<ScriptBean>();
		for(int i =0;i<scriptBeanList.size();i++) {
			if(scriptBeanList.get(i).getRoleNo()==roleSelect) {
				scriptBeanSelect.add(scriptBeanList.get(i));
			}
		}
		for(ScriptBean scriptBean :scriptBeanSelect) {
			System.out.println(scriptBean.getRoleName());
			System.out.println(scriptBean.getScriptPosition());
		}
		// 已取得postNo新增時的主鍵
		if(postNo!=null) {
			String postOK  ;
			// 先新增總共需要的筆數
			RecordService rs =  ctx.getBean(RecordService.class);
			try {
				for(int i = 0 ;i<scriptBeanSelect.size();i++) {
					Integer n  =scriptStartNo+(scriptBeanSelect.get(i).getScriptPosition()-1);
					
					 recordPath  = jsonObject.get(memberNo+"_"+clipNo+"_"+String.valueOf(n)).getAsString();
					    System.out.println(memberNo+"_"+clipNo+"_"+String.valueOf(n));
						byte[] recordByte = Base64.getMimeDecoder().decode(recordPath.replaceAll("\\n","").replaceAll("\uFEFF",""));
						File file = null;
						//String filePath= "/Users/chian0418/Downloads/_JSP_JDBC/tomcat9/webapps/Whiloud/data/record/";
						String filePath = "C:\\_JSP\\eclipse-workspaceJSP\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Whiloud\\data\\record";
						
						String fileName =String.valueOf(postNo)+"_"+n+".wav";
						file  =new File(filePath,fileName);
						try(FileOutputStream fos = new FileOutputStream(file,true)){
							fos.write(recordByte);
						}catch(Exception e) {
							e.printStackTrace();
						}
					String servletRecordPath  = "/data/record/"+fileName;
					RecordBean rb = new RecordBean(null, servletRecordPath, creationDateTime);
					
//					rb.setMb(ctx.getBean(MemberService.class).getMember(String.valueOf(memberNo)));
//					rb.setPb(ctx.getBean(PostService.class).getPostDetailBean(String.valueOf(postNo)));
//					rb.setSb(ctx.getBean(ScriptService.class).getScript(String.valueOf(n)));
//					rs.saveRecord(rb);
					
					rs.updateRecord(postNo, n, servletRecordPath, Integer.valueOf(memberNo));
					System.out.println("postNo = " + postNo + ", scriptNo = " + scriptNo + ", servletRecordPath=" + servletRecordPath+   ",memberNo" + memberNo);
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
