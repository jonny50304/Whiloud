package friend.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import friend.model.FriendBean;
import friend.repository.FriendDao;
import friend.repository.impl.FriendDaoImpl;
import friend.service.FriendService;
import init.HibernateUtils;
import register.model.MemberBean;
@Service
public class FriendServiceImpl implements FriendService{
	@Autowired
	FriendDao dao  ;
	public FriendServiceImpl() {
	
	}
	@Transactional
	@Override
	public List<FriendBean> getAllFriends(Integer memberNo) {
		List<FriendBean> friendBeanList = null;

		friendBeanList = dao.getAllFriends(memberNo);

		return friendBeanList;
	}
	
	@Transactional
	@Override
	public List<FriendBean> getAllFriendsOrderByUnread(Integer memberNo) {
		List<FriendBean> friendBeanList = null;

		friendBeanList = dao.getAllFriendsOrderByUnread(memberNo);

		return friendBeanList;
	}
	
	
	@Transactional
	@Override
	public Boolean isFriend(Integer memberNo, Integer friendNo) {
		Boolean isFriend = null;

			isFriend = dao.isFriend(memberNo, friendNo);

		return isFriend;
	}
	
	@Transactional
	@Override
	public void cancelFriend(MemberBean mb1, MemberBean mb2) {
		dao.cancelFriend(mb1,mb2);
		
	}
	

	

}
