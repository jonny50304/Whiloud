package clip.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clip.model.ScriptBean;
import clip.repository.ScriptDao;
import clip.repository.impl.ScriptDaoImpl_Hibernate;
import clip.service.ScriptService;
import init.HibernateUtils;
@Service
public class ScriptServiceImpl implements ScriptService{
	@Autowired
	ScriptDao dao;
	

	public ScriptServiceImpl() {
	
	}
	@Transactional
	@Override
	public List<ScriptBean> getScriptList(String clipNo) {
		List<ScriptBean> scriptList = null;

			scriptList =dao.getScriptList(clipNo);

		return scriptList;
	}
	@Transactional
	@Override
	public ScriptBean getScript(String ScriptNo) {
		ScriptBean sb = null;

			sb =dao.getScript(ScriptNo);

		return sb;
	}

}
