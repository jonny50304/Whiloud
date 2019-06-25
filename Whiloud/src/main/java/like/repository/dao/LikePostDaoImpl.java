package like.repository.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import like.model.LikePostBean;
import like.repository.LikePostDao;
import post.model.PostBean;
import register.model.MemberBean;

@Repository
public class LikePostDaoImpl implements LikePostDao{
	
	@Autowired
	SessionFactory factory;
	
	public LikePostDaoImpl(){
	
	}
	
	@Override
	public List<LikePostBean> getLikeCountByMemberNo(Integer memberNo) {
		List<LikePostBean> likePostBeanList = null;
		Session session = factory.getCurrentSession();
		
		String hql ="From LikePostBean lpb WHERE lpb.pb.mb1.memberNo =:memberNo OR lpb.pb.mb2.memberNo =:memberNo";
		likePostBeanList = session.createQuery(hql).setParameter("memberNo", memberNo).list();
		
		return likePostBeanList;
	}

	@Override
	public void likePost(MemberBean mb, PostBean pb) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		LikePostBean lpb = new LikePostBean(null,mb,pb,ts);
		Session session = factory.getCurrentSession();
		session.save(lpb);
	}

	@Override
	public Boolean isLiked(Integer memberNo, Integer postNo) {
		Session session = factory.getCurrentSession();
		String hql = "FROM LikePostBean WHERE lpb.mb.memberNo =:memberNo AND lpb.pb.postNo =:postBean";
		
		try {
			session.createQuery(hql).setParameter("memberNo", memberNo).setParameter("postNo", postNo).getSingleResult();
		}catch(NoResultException nre) {
			return false;
		}catch(NonUniqueResultException nure) {
			nure.printStackTrace();
			return null;
		}
		
		return true;
		
		
	}

	@Override
	public Integer getLikeCounts(Integer postNo) {
		List<LikePostBean> likePostBeanList = null;
		Session session = factory.getCurrentSession();
		String hql = "FROM LikePostBean WHERE lpb.pb.postNo =:postNo";
		
		likePostBeanList = session.createQuery(hql).setParameter("postNo", postNo).list();
		
		
		return likePostBeanList.size();
	}

	@Override
	public void dislikePost(MemberBean mb, PostBean pb) {
		
		Session session = factory.getCurrentSession();
		String hql = "DELETE LikePostBean lpb WHERE lpb.pb.postNo =:postNo AND lpb.mb.memberNo=:memberNo";
		session.createQuery(hql)
			.setParameter("memberNo", mb.getMemberNo())
			.setParameter("postNo", pb.getPostNo())
			.executeUpdate();
		
	}

	@Override
	public List<LikePostBean> getLikeListByMemberNo(Integer memberNo) {
		List<LikePostBean> likePostBeanList = null;
		Session session = factory.getCurrentSession();
		
		String hql ="From LikePostBean lpb WHERE lpb.mb.memberNo =:memberNo";
		likePostBeanList = session.createQuery(hql).setParameter("memberNo", memberNo).list();
		
		return likePostBeanList;
	}

}
