package chat.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chat.model.ChatBean;
import chat.repository.ChatDao;
import chat.repository.impl.ChatDaoImpl_Hibernate;
import chat.service.ChatService;
import init.HibernateUtils;
import register.model.MemberBean;
@Service
public class ChatServiceImpl implements ChatService{
	@Autowired
	ChatDao dao ;
	
	public ChatServiceImpl() {
	
	}
	@Transactional
	@Override
	public List<ChatBean> getChat(String memberNo1, String memberNo2) {
		List<ChatBean> chatList= null;

			chatList = dao.getChat(memberNo1, memberNo2);

		return chatList;
	}
	
	@Transactional
	@Override
	public ChatBean sendChat(MemberBean mb1, MemberBean mb2, String message) {
		
		ChatBean cb = dao.sendChat(mb1, mb2, message);
		return cb;
		
	}

}
