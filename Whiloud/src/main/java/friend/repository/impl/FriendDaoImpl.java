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
import friend.repository.FriendDao;
import init.HibernateUtils;
import member.model.Old_FriendListBean;
import register.model.MemberBean;
@Repository
public class FriendDaoImpl implements FriendDao {
	@Autowired
	SessionFactory factory ;
	
	public FriendDaoImpl(){
		
	}
	
	@Override
	public List<FriendBean> getAllFriends(Integer memberNo) {
		List<FriendBean> friendBeanList = null;
		Session session = factory.getCurrentSession();
		
		String hql ="From FriendBean fb WHERE fb.memberNo.memberNo =:memberNo";
		friendBeanList = session.createQuery(hql).setParameter("memberNo", memberNo).list();
		
		return friendBeanList;
	}
	
	@Override
	public List<FriendBean> getAllFriendsOrderByUnread(Integer memberNo) {
		List<FriendBean> friendBeanList = null;
		Session session = factory.getCurrentSession();
		
		String hql ="From FriendBean fb WHERE fb.memberNo.memberNo =:memberNo ORDER BY unread DESC";
		friendBeanList = session.createQuery(hql).setParameter("memberNo", memberNo).list();
		
		return friendBeanList;
	}
	
	@Override
	public Boolean isFriend(Integer memberNo, Integer friendNo) {
		FriendBean fb = null;
		Session session = factory.getCurrentSession();
		
		String hql ="From FriendBean fb WHERE fb.memberNo.memberNo =:memberNo AND fb.friendNo.memberNo =:friendNo";
		try {
			fb = (FriendBean)session
					.createQuery(hql)
					.setParameter("memberNo", memberNo)
					.setParameter("friendNo", friendNo)
					.getSingleResult();
		}catch(NoResultException e){
			fb = null;
		}catch(NonUniqueResultException e) {
			System.out.println("丟出了不應該有的NonUniqueResultException");
			e.printStackTrace();
		}
		
		
		return fb == null? false: true;
	}

	@Override
	public void cancelFriend(MemberBean mb1, MemberBean mb2) {
		Session session = factory.getCurrentSession();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		
		
		String hql3 = "DELETE FriendBean fb WHERE "
				+ " (fb.memberNo.memberNo =:memberNo AND fb.friendNo.memberNo =:friendNo) "
				+ " OR (fb.friendNo.memberNo =:memberNo AND fb.memberNo.memberNo =:friendNo)";
		session.createQuery(hql3)
			.setParameter("memberNo", mb1.getMemberNo())
			.setParameter("friendNo", mb2.getMemberNo())
			.executeUpdate();
	}



}
