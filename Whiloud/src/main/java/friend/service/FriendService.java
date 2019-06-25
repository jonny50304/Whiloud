package friend.service;

import java.util.List;

import friend.model.FriendBean;
import register.model.MemberBean;

public interface FriendService {
	public List<FriendBean> getAllFriends(Integer memberNo);

	public Boolean isFriend(Integer memberNo, Integer friendNo);

	public List<FriendBean> getAllFriendsOrderByUnread(Integer memberNo);

	public void cancelFriend(MemberBean mb1, MemberBean mb2);


}
