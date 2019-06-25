package member.service;

import java.util.List;

import member.model.Old_FriendListBean;

public interface OldFriendService {
	
	public List<Old_FriendListBean> getAllFriendList(String memberNo);
	
	public List<Old_FriendListBean> getAllFriendRequest(String memberNo);
	
	public List<Old_FriendListBean> getAllFriendConfirm(String memberNo);
	
}
