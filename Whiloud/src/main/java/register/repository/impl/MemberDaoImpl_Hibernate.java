	package register.repository.impl;

import java.sql.Connection;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import init.HibernateUtils;
import register.model.MemberBean;
import register.repository.MemberDao;
@Repository
public class MemberDaoImpl_Hibernate implements MemberDao{
	
	
	@Autowired
	SessionFactory factory ;
	public MemberDaoImpl_Hibernate() {
		
	
	
	}
	@Override
	public boolean idExists(String id) {
		// TODO Auto-generated method stub
		Session session =factory.getCurrentSession();
		boolean exist =false ;
		String hql = "FROM MemberBean m WHERE m.account = :account";
		try {
			MemberBean mb = (MemberBean)session.createQuery(hql).setParameter("account", id).uniqueResult();
			if(mb!=null) {
				exist=true;
			}
		}catch(NoResultException e){
			exist =false;
		}catch (NonUniqueResultException e) {
			exist=true;
		}
		return exist;
	}

	@Override
	public int saveMember(MemberBean mb) {
		// TODO Auto-generated method stub
		int n = 0 ;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
	}

	@Override
	public MemberBean queryMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberBean checkIDPassword(String account, String PASSWORD) {
		MemberBean mb = null;
		Session session= factory.getCurrentSession();
		String hql =  "From MemberBean m WHERE m.account = :account and m.PASSWORD=:PASSWORD";
		mb = (MemberBean) session.createQuery(hql).setParameter("account", account).setParameter("PASSWORD", PASSWORD).uniqueResult();
		return mb;
	}

	@Override
	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean nicknameExists(String nickname) {
		Session session =factory.getCurrentSession();
		boolean exist =false ;
		String hql = "FROM MemberBean m WHERE m.nickname = :nickname";
		try {
			MemberBean mb = (MemberBean)session.createQuery(hql).setParameter("nickname", nickname).uniqueResult();
			if(mb!=null) {
				exist=true;
			}
		}catch(NoResultException e){
			exist =false;
		}catch (NonUniqueResultException e) {
			exist=true;
		}
		return exist;
	}
	@Override
	public MemberBean getMember(String id) {
		MemberBean mb= null;

			Integer memberNo = Integer.valueOf(id);
			Session session = factory.getCurrentSession();
			String hql = "From MemberBean m Where m.memberNo =:memberNo";
			 mb =(MemberBean) session.createQuery(hql).setParameter("memberNo", memberNo).uniqueResult();
			return mb;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> getAllMember() {
		List<MemberBean> result =null;

		Session session = factory.getCurrentSession();
		String hql = "From MemberBean";
		result = session.createQuery(hql).list();
	
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBean> getAllMemberByCreationDateTime() {
		List<MemberBean> result =null;

		Session session = factory.getCurrentSession();
		String hql = "From MemberBean ORDER BY creationDateTime DESC";
		result = session.createQuery(hql).list();
	
		return result;
	}
	
	
	@Override
	public void savePicture(Integer memberNo, String picturePath) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE MemberBean SET picturePath =:picturePath WHERE memberNo=:memberNo";
		session.createQuery(hql)
			.setParameter("picturePath", picturePath)
			.setParameter("memberNo", memberNo).executeUpdate();
		
	}
	
	
	@Override
	public void updateInformation(MemberBean mb) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE MemberBean SET phone =:phone, birthday=:birthday, introduction =:introduction, interest =:interest WHERE memberNo=:memberNo";
		session.createQuery(hql)
			.setParameter("phone",mb.getPhone())
			.setParameter("birthday",mb.getBirthday())
			.setParameter("introduction",mb.getIntroduction())
			.setParameter("interest", mb.getInterest())
			.setParameter("memberNo", mb.getMemberNo())
			.executeUpdate();
	}
	@Override
	public void updatePassword(Integer memberNo, String password) {
		Session session = factory.getCurrentSession();
		String hql = "UPDATE MemberBean SET password =: password WHERE memberNo=:memberNo";
		session.createQuery(hql)
			.setParameter("password", password)
			.setParameter("memberNo", memberNo)
			.executeUpdate();
		
	}
	@Override
	public MemberBean checkFbId(String account) {
		MemberBean mb = null;
		Session session= factory.getCurrentSession();
		String hql =  "From MemberBean m WHERE m.account = :account";
		mb = (MemberBean) session.createQuery(hql).setParameter("account", account).uniqueResult();
		return mb;
	}
	
}

