package clip.repository.impl;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import clip.model.ScriptBean;
import clip.repository.ScriptDao;
import init.HibernateUtils;

@Repository
public class ScriptDaoImpl_Hibernate implements ScriptDao {
	@Autowired
	SessionFactory factory;

	public ScriptDaoImpl_Hibernate() {
		
		
	}
	
	public List<ScriptBean> getScriptList(String clipNo) {
		List<ScriptBean> scriptBeanList = null;

		Integer ClipNo = Integer.valueOf(clipNo);
		Session session = factory.getCurrentSession();
		String hql = "From ScriptBean sb Where sb.cb.clipNo = :clipNo";
		scriptBeanList = session.createQuery(hql).setParameter("clipNo", ClipNo).list();
		return scriptBeanList;
	}

	@Override
	public ScriptBean getScript(String scriptNo) {
		
		Session session = factory.getCurrentSession();
		String hql = "From ScriptBean sb Where sb.scriptNo = :scriptNo";
		ScriptBean sb = (ScriptBean)session.createQuery(hql)
				.setParameter("scriptNo", Integer.valueOf(scriptNo))
				.uniqueResult();
		return sb;

	}

}
