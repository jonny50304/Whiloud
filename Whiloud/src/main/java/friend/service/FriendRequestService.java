package friend.service;

import java.util.List;

import friend.model.FriendRequestBean;
import register.model.MemberBean;

public interface FriendRequestService {
	List<FriendRequestBean> getAllInvite(Integer memberNo);
	List<FriendRequestBean> getAllWasInvited(Integer memberNo);
	void becomeFriend(MemberBean mb1, MemberBean mb2);
	void sendRequest(MemberBean mb1, MemberBean mb2);
	Boolean isInRequest(Integer memberNo, Integer friendNo);
	Boolean isWaitingToAccept(Integer memberNo, Integer friendNo);
	void cancelRequest(MemberBean mb1, MemberBean mb2);
	void rejectRequest(MemberBean mb1, MemberBean mb2);
}
