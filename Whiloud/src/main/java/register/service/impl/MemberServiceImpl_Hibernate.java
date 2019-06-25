package register.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import init.HibernateUtils;
import register.model.MemberBean;
import register.repository.MemberDao;
import register.repository.impl.MemberDaoImpl_Hibernate;
import register.service.MemberService;
@Service
public class MemberServiceImpl_Hibernate implements MemberService{
	@Autowired
	MemberDao dao;

	public MemberServiceImpl_Hibernate() {
	
	}
	@Transactional
	@Override
	public boolean idExists(String id) {

		boolean exist =false;

			exist = dao.idExists(id);

		return exist;
	}

	@Transactional
	@Override
	public int saveMember(MemberBean mb) {
		int count = 0;

			dao.saveMember(mb);
			count++;

		
		return count;
	}

	@Override
	public MemberBean queryMember(String id) {
	
		return null;
	}
	@Transactional
	@Override
	public boolean nicknameExists(String nickname) {
		boolean exist =false;

			exist = dao.nicknameExists(nickname);

		return exist;
	}
	@Transactional
	@Override
	public MemberBean getMember(String id) {
		MemberBean mb =null;

			mb = dao.getMember(id);

	     return mb;
	}
	@Transactional
	@Override
	public List<MemberBean> getAllMember(){
		List<MemberBean> result =null;

			result = dao.getAllMember();

		return result;
	}
	
	@Transactional
	@Override
	public List<MemberBean> getAllMemberByCreationDateTime(){
		List<MemberBean> result =null;

			result = dao.getAllMemberByCreationDateTime();

		return result;
	}
	
	
	
	@Transactional
	@Override
	public MemberBean checkIdPassword(String account, String PASSWORD) {
		MemberBean  mb= null;

			mb = dao.checkIDPassword(account, PASSWORD);

		return mb;
	}
	
	@Transactional
	@Override
	public void savePicture(Integer memberNo, String picturePath) {
		dao.savePicture(memberNo, picturePath);
		
	}
	
	@Transactional
	@Override
	public int updateInformation(MemberBean mb) {
		int count=0;
		dao.updateInformation(mb);
		count++;

	
	return count;
	}
	
	@Transactional
	@Override
	public void updatePassword(Integer memberNo, String password) {
		dao.updatePassword(memberNo, password);
		
	}
	

}
