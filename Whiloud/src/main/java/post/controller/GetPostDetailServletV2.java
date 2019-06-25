package post.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import comment.model.CommentBean;
import comment.service.CommentService;
import like.model.LikePostBean;
import like.service.LikePostService;
import post.model.PostBean;
import post.model.RecordBean;
import post.service.PostService;
import post.service.RecordService;
import post.service.impl.PostServiceImpl;
import post.service.impl.RecordServiceImpl;

@WebServlet("/GetPostDetailServletV2")
public class GetPostDetailServletV2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetPostDetailServletV2");
		request.setCharacterEncoding("UTF-8");
		
		String postNo = request.getParameter("postNo");
		try {
			//開啟service
			ServletContext  sc = getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
			PostService postservice = ctx.getBean(PostService.class);
			CommentService cs = ctx.getBean(CommentService.class);
			RecordService recordservice = ctx.getBean(RecordService.class);
			
			//獲取bean
			PostBean pdb = postservice.getPostDetailBean(postNo);
			List<CommentBean> cbl = cs.getCommentByPostNo(Integer.parseInt(postNo));
			List<RecordBean> recordList = recordservice.getRecordList(postNo);
			
//			//判斷是否已按過讚
//			for(LikePostBean lpb: pdb.getLikePostBean()) {
//				lpb.getMb()
//			}
			
			//準備傳回瀏覽器
			request.setAttribute("postDetailBean", pdb);
			request.setAttribute("recordList", recordList);
			request.setAttribute("commentBeanList", cbl);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request
				.getRequestDispatcher("/player/getPost.jsp");
		// 請容器代為呼叫下一棒程式
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}
