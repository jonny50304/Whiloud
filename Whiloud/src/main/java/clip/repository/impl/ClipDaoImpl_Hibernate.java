package clip.repository.impl;

import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import clip.model.ClipBean;
import clip.repository.ClipDao;
import init.HibernateUtils;
@Repository
public class ClipDaoImpl_Hibernate implements ClipDao{
	@Autowired
	SessionFactory factory;
	public ClipDaoImpl_Hibernate() {
		

	
	}
	
	public ClipBean getClip(String clipNo){
		ClipBean clipBean = null;
		Session session = factory.getCurrentSession();
		Integer ClipNo = Integer.valueOf(clipNo);
		String hql = "From ClipBean cb Where cb.clipNo=:clipNo";
		clipBean =  (ClipBean) session.createQuery(hql).setParameter("clipNo", ClipNo).uniqueResult();
		return clipBean;
	}
	
	public List<ClipBean> getAllClips(){
		List<ClipBean> clipList = null;

		Session session = factory.getCurrentSession();
		String hql = "From ClipBean ORDER BY creationDateTime DESC";
		clipList =  session.createQuery(hql).list();
		return clipList;
	}
}
