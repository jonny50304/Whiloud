package friend.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import friend.model.FriendRequestBean;
import friend.repository.FriendRequestDao;
import friend.repository.impl.FriendRequestDaoImpl;
import friend.service.FriendRequestService;
import init.HibernateUtils;
import register.model.MemberBean;
@Service
public class FriendRequestServiceImpl implements FriendRequestService {
	@Autowired
	FriendRequestDao dao  ;
	
	public FriendRequestServiceImpl() {
	
	}
	@Transactional
	@Override
	public List<FriendRequestBean> getAllInvite(Integer memberNo) {
		List<FriendRequestBean> FriendRequestBeanList = null;

			FriendRequestBeanList = dao.getAllInvite(memberNo);

		return FriendRequestBeanList;
	}
	@Transactional
	@Override
	public List<FriendRequestBean> getAllWasInvited(Integer memberNo) {
		List<FriendRequestBean> FriendRequestBeanList = null;

			FriendRequestBeanList = dao.getAllWasInvited(memberNo);

		return FriendRequestBeanList;
	}
	@Transactional
	@Override
	public void becomeFriend(MemberBean mb1, MemberBean mb2) {

			dao.becomeFriend(mb1, mb2);

		
	}
	@Transactional
	@Override
	public void sendRequest(MemberBean mb1, MemberBean mb2) {

			dao.sendRequest(mb1, mb2);

		
	}
	@Transactional
	@Override
	public Boolean isInRequest(Integer memberNo, Integer friendNo) {
		Boolean isInRequest = null;

			isInRequest = dao.isInRequest(memberNo, friendNo);

		return isInRequest;
		
	}
	@Transactional
	@Override
	public Boolean isWaitingToAccept(Integer memberNo, Integer friendNo) {
		return isInRequest(friendNo, memberNo); //直接調用同類別方法，參數剛好相反
	}
	
	@Transactional
	@Override
	public void cancelRequest(MemberBean mb1, MemberBean mb2) {
		dao.cancelRequest(mb1, mb2);
		
	}
	
	@Transactional
	@Override
	public void rejectRequest(MemberBean mb1, MemberBean mb2) {
		dao.rejectRequest(mb1, mb2);
		
	}

}
