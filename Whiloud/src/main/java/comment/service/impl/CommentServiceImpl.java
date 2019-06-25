package comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comment.model.CommentBean;
import comment.repository.CommentDao;
import comment.service.CommentService;
import friend.repository.FriendDao;
import post.model.PostBean;
import register.model.MemberBean;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao dao  ;
	
	public CommentServiceImpl() {
		
	}
	
	@Transactional
	@Override
	public List<CommentBean> getCommentByPostNo(Integer postNo) {
		List<CommentBean> cbl = null;
		cbl = dao.getCommentByPostNo(postNo);
		return cbl;
	}
	
	@Transactional
	@Override
	public void insertComment(MemberBean mb, PostBean pb, String text, String ip) {
		dao.insertComment(mb,pb,text,ip);
		
	}



}

