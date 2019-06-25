package post.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import init.HibernateUtils;
import post.model.RecordBean;
import post.repository.RecordDao;
@Repository
public class RecordDaoImpl_Hibernate implements RecordDao{
	@Autowired
	SessionFactory factory;
	public RecordDaoImpl_Hibernate() {
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<RecordBean> getRecordList(String postNo){
		Integer PostNo  = Integer.valueOf(postNo);
		List<RecordBean> recordList = null;
		Session session = factory.getCurrentSession();
		String hql = "From RecordBean rb Where rb.pb.postNo =:postNo order by rb.sb.scriptNo asc";
		recordList =  session.createQuery(hql).setParameter("postNo", PostNo).list();
		
		return recordList;
	}

	@Override
	public int saveRecord(RecordBean rb) {
		int n = 0 ;
		Session session = factory.getCurrentSession();
		session.save(rb);
		n++;
		return n;
	}

	@Override
	public int updateRecord(Integer postNo, Integer scriptNo, String recordPath, Integer memberNo) {
		int n = 0 ;
		Session session = factory.getCurrentSession();

		String hql="UPDATE RecordBean rb "
				+ " SET rb.recordPath=:recordPath, rb.mb.memberNo =:memberNo "
				+ " where rb.pb.postNo =:postNo AND rb.sb.scriptNo=:scriptNo";
		Query query = session.createQuery(hql);
		query.setParameter("memberNo", memberNo);
		query.setParameter("recordPath", recordPath);
		query.setParameter("postNo", postNo);
		query.setParameter("scriptNo", scriptNo);
		n = query.executeUpdate();
		
		
		return n;
	}

	@Override
	public int updateNotDoneRecord(Integer postNo, Integer memberNo2, Integer scriptNo, String recordPath) {
		int n = 0 ;
		Session session = factory.getCurrentSession();

		String hql="UPDATE RecordBean rb "
				+ " SET rb.recordPath=:recordPath, rb.mb.memberNo2 =:memberNo2 "
				+ " where rb.pb.postNo =:postNo AND rb.sb.scriptNo=:scriptNo";
		Query query = session.createQuery(hql);
		query.setParameter("memberNo2", memberNo2);
		query.setParameter("recordPath", recordPath);
		query.setParameter("postNo", postNo);
		query.setParameter("scriptNo", scriptNo);
		n = query.executeUpdate();
		
		
		return n;
	}

	@Override
	public RecordBean getRecordBean(Integer recordNo) {
		RecordBean rb = null;
		Session session = factory.getCurrentSession();
		String hql = "From RecordBean WHERE recordNo =:recordNo";
		rb = (RecordBean) session.createQuery(hql).setParameter("recordNo", recordNo).getSingleResult();
		return rb;
	}
}
