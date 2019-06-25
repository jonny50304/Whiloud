package init.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import clip.model.ClipBean;
import clip.service.ClipService;
import friend.model.FriendBean;
import friend.service.FriendService;
import post.model.PostBean;
import post.service.PostService;
import register.model.MemberBean;
import register.service.MemberService;

/**
 * Servlet Filter implementation class EnterIndex
 */
@WebFilter("/main.jsp")
public class Filter02EnterMain implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("進入Filter02EnterMain");
		
		//判斷是否已經登入，如果沒登入，就以訪客方式拜訪
		HttpSession session = ((HttpServletRequest)request).getSession(false);
		if(session==null) {
			
			System.out.println("即將以訪客身分登入");
			//---------------------
			//    TODO:訪客登入
			//---------------------
		}
		
		MemberBean LoginMemberBean = (MemberBean) session.getAttribute("LoginOK");
		
		request.setCharacterEncoding("UTF-8");
		ServletContext sc = request.getServletContext();
		WebApplicationContext ctx  =WebApplicationContextUtils.getWebApplicationContext(sc);
		ClipService cs =ctx.getBean(ClipService.class);
		PostService ps = ctx.getBean(PostService.class);
		MemberService ms =  ctx.getBean(MemberService.class);
		FriendService fs =  ctx.getBean(FriendService.class);
		
		List<ClipBean> clipBeanList = cs.getAllClips();
		List<PostBean> postBeanList = ps.getAllDonePosts();
		List<PostBean> cooperationBeanList = ps.getAllCooperation();
		List<MemberBean> memberBeanList =  ms.getAllMemberByCreationDateTime();
		List<MemberBean> suggestFriendList = new ArrayList<>();
		List<FriendBean> friendBeanList =  fs.getAllFriendsOrderByUnread(LoginMemberBean.getMemberNo());
		
		//建置建議好友的list
		for(MemberBean mb : memberBeanList){
			if(!fs.isFriend(LoginMemberBean.getMemberNo(), mb.getMemberNo())
					&& mb.getMemberNo() !=1
					&& mb.getMemberNo() !=LoginMemberBean.getMemberNo()){
				suggestFriendList.add(mb);
			}
		}
		
		request.setAttribute("clipBeanList", clipBeanList);
		request.setAttribute("postBeanList", postBeanList);
		request.setAttribute("suggestFriendList", suggestFriendList);
		request.setAttribute("friendBeanList", friendBeanList);
		request.setAttribute("cooperationBeanList", cooperationBeanList);
		//request.setAttribute("memberBeanList", memberBeanList);
//		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//		// 請容器代為呼叫下一棒程式
//		rd.forward(request, response);
		
		chain.doFilter(request, response);
	}

	
    public Filter02EnterMain() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

}
