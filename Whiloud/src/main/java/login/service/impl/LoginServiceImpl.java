package login.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import init.HibernateUtils;
import login.service.LoginService;
import register.model.MemberBean;
import register.repository.MemberDao;
import register.repository.impl.MemberDaoImpl_Hibernate;


// 登入時系統必須執行的checkIDPassword()功能交由 MemberDao來完成 
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	MemberDao  dao = null;
	public LoginServiceImpl() {

	}
	@Transactional
	public MemberBean checkIDPassword(String account, String PASSWORD) {
		MemberBean  mb= null;

			mb = dao.checkIDPassword(account, PASSWORD);

		return mb;
	}
	
	@Transactional
	@Override
	public MemberBean checkFbId(String account) {
		MemberBean  mb= null;

		mb = dao.checkFbId(account);
			
		return mb;
	}
}
