package chat.service;

import java.util.List;

import chat.model.ChatBean;
import register.model.MemberBean;



public interface ChatService {
	public List<ChatBean> getChat(String memberNo1, String memberNo2);
	
	public ChatBean sendChat(MemberBean mb1, MemberBean mb2, String message);
}
