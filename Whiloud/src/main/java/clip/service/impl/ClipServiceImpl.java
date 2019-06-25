package clip.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clip.model.ClipBean;
import clip.repository.ClipDao;
import clip.repository.impl.ClipDaoImpl_Hibernate;
import clip.service.ClipService;
import init.HibernateUtils;
@Service
public class ClipServiceImpl implements ClipService{
	@Autowired
	ClipDao dao ;
;
	public ClipServiceImpl() {
	
	}
	@Transactional
	@Override
	public ClipBean getClip(String clipNo) {
		ClipBean clipBean = null;

			clipBean =dao.getClip(clipNo);

		return clipBean;
	}
	@Transactional
	@Override
	public List<ClipBean> getAllClips() {
		// TODO Auto-generated method stub
		List<ClipBean>clipList=null;

			clipList =dao.getAllClips();

		return clipList;
	}

}
