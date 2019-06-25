package comment.repository.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comment.model.CommentBean;
import comment.repository.CommentDao;
import post.model.PostBean;
import register.model.MemberBean;

@Repository
public class CommentDaoImpl implements CommentDao {
	@Autowired
	SessionFactory factory;
	
	public CommentDaoImpl() {
		
	}
	
	@Override
	public List<CommentBean> getCommentByPostNo(Integer postNo) {
		List<CommentBean> cbl = null;
		Session session = factory.getCurrentSession();
		String hql = "From CommentBean WHERE postNo =:postNo";
		cbl = session.createQuery(hql).setParameter("postNo", postNo).list();
		
		return cbl;
	}

	@Override
	public void insertComment(MemberBean mb, PostBean pb, String text, String ip) {
		Session session = factory.getCurrentSession();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		CommentBean cb = new CommentBean(null,mb,pb,text,ts,ip);
		session.save(cb);
		
		
	}


}
