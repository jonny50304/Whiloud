package chat.repository.impl;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import chat.model.ChatBean;
import chat.repository.ChatDao;
import init.HibernateUtils;
import register.model.MemberBean;
@Repository
public class ChatDaoImpl_Hibernate implements ChatDao{
	@Autowired
	SessionFactory factory ;
	public ChatDaoImpl_Hibernate() {
	
	
	}
	
	@SuppressWarnings("unchecked")
	public List<ChatBean> getChat(String memberNo1, String memberNo2){
		List<ChatBean> chatList =null;

		Session session = factory.getCurrentSession();
		Integer MemberNo1  =  Integer.valueOf(memberNo1);
		Integer MemberNo2  =  Integer.valueOf(memberNo2);
		String hql = "From ChatBean ch "
				+ " Where (ch.mb1.memberNo=:memberNo1 AND ch.mb2.memberNo= :memberNo2) "
				+ " OR (ch.mb1.memberNo=:memberNo2 AND ch.mb2.memberNo= :memberNo1) "
				+ " ORDER BY ch.creationDateTime ";
		chatList = session.createQuery(hql).setParameter("memberNo1", MemberNo1).setParameter("memberNo2", MemberNo2).list();
		for(ChatBean cb :chatList) {
			System.out.println(cb.getChatNo());
		}
		return chatList;
	}

	@Override
	public ChatBean sendChat(MemberBean mb1, MemberBean mb2, String message) {
		Session session = factory.getCurrentSession();
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		ChatBean cb = new ChatBean();

		cb.setMb1(mb1);
		cb.setMb2(mb2);
		cb.setMessage(message);
		cb.setRead(false);
		cb.setCreationDateTime(ts);
		session.save(cb);
		
		
		return cb;

	}
}
