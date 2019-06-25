package friend.repository.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import friend.model.FriendBean;
import friend.model.FriendRequestBean;
import friend.repository.FriendRequestDao;
import init.HibernateUtils;
import register.model.MemberBean;
import register.service.impl.MemberServiceImpl_Hibernate;
@Repository
public class FriendRequestDaoImpl implements FriendRequestDao {
	@Autowired
	SessionFactory factory;
	
	public FriendRequestDaoImpl(){
	
	}
	
	@Override
	public List<FriendRequestBean> getAllInvite(Integer memberNo) {
		List<FriendRequestBean> friendRequestBeanList = null;
		Session session = factory.getCurrentSession();
		
		String hql ="From FriendRequestBean frb WHERE frb.memberNo.memberNo =:memberNo";
		friendRequestBeanList = session.createQuery(hql).setParameter("memberNo", memberNo).list();
		
		return friendRequestBeanList;
	}

	@Override
	public List<FriendRequestBean> getAllWasInvited(Integer memberNo) {
		List<FriendRequestBean> friendRequestBeanList = null;
		Session session = factory.getCurrentSession();
		
		String hql = "From FriendRequestBean frb WHERE frb.friendNo.memberNo =:memberNo";
		friendRequestBeanList = session.createQuery(hql).setParameter("memberNo", memberNo).list();
		
		return friendRequestBeanList;
	}

	@Override
	public void becomeFriend(MemberBean mb1, MemberBean mb2) {
		Session session = factory.getCurrentSession();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		
		FriendBean fb1 = new FriendBean(null, mb1, mb2, ts, 0);
		FriendBean fb2 = new FriendBean(null, mb2, mb1, ts, 0);
		
		session.save(fb1);
		session.save(fb2);
		
		String hql3 = "DELETE FriendRequestBean frb WHERE frb.friendNo.memberNo =:memberNo";
		session.createQuery(hql3).setParameter("memberNo", mb1.getMemberNo()).executeUpdate();
		
	}

	@Override
	public void sendRequest(MemberBean mb1, MemberBean mb2) {
		Session session = factory.getCurrentSession();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		
		FriendRequestBean frb = new FriendRequestBean(null, mb1, mb2, ts);
		
		session.save(frb);
		
	}

	@Override
	public Boolean isInRequest(Integer memberNo, Integer friendNo) {
		FriendRequestBean frb = null;
		Session session = factory.getCurrentSession();
		
		String hql ="From FriendRequestBean frb WHERE frb.memberNo.memberNo =:memberNo AND frb.friendNo.memberNo =:friendNo";
		try {
			frb = (FriendRequestBean)session
					.createQuery(hql)
					.setParameter("memberNo", memberNo)
					.setParameter("friendNo", friendNo)
					.getSingleResult();
		}catch(NoResultException e){
			frb = null;
		}catch(NonUniqueResultException e) {
			System.out.println("丟出了不應該有的NonUniqueResultException");
			e.printStackTrace();
		}
		
		
		return frb == null? false: true;
	}

	
	@Override
	public void cancelRequest(MemberBean mb1, MemberBean mb2) {
		Session session = factory.getCurrentSession();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		
		
		String hql3 = "DELETE FriendRequestBean frb WHERE frb.memberNo.memberNo =:memberNo AND frb.friendNo.memberNo =:friendNo";
		session.createQuery(hql3)
			.setParameter("memberNo", mb1.getMemberNo())
			.setParameter("friendNo", mb2.getMemberNo())
			.executeUpdate();
			
	}
	
	
	@Override
	public void rejectRequest(MemberBean mb1, MemberBean mb2) {
		Session session = factory.getCurrentSession();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		
		
		String hql3 = "DELETE FriendRequestBean frb WHERE frb.friendNo.memberNo =:memberNo AND frb.memberNo.memberNo =:friendNo";
		session.createQuery(hql3)
			.setParameter("memberNo", mb1.getMemberNo())
			.setParameter("friendNo", mb2.getMemberNo())
			.executeUpdate();
			
	}
	

	
}
