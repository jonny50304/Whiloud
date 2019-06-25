package chat.repository;

import java.util.List;

import chat.model.ChatBean;
import register.model.MemberBean;

public interface ChatDao {
	public List<ChatBean> getChat(String memberNo1, String memberNo2);

	public ChatBean sendChat(MemberBean mb1, MemberBean mb2, String message);
}
