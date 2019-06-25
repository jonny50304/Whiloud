package post.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import init.HibernateUtils;
import post.model.RecordBean;
import post.repository.RecordDao;
import post.repository.impl.RecordDaoImpl_Hibernate;
import post.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService{
	@Autowired
	RecordDao dao;
	public RecordServiceImpl() {
	
	}
	@Transactional
	@Override
	public List<RecordBean> getRecordList(String postNo) {
		List<RecordBean> recordList =null;

			recordList =dao.getRecordList(postNo);

		return recordList;
	}
	@Transactional
	@Override
	public int saveRecord(RecordBean rb) {
		int count = 0;

			dao.saveRecord(rb);
			count++;

		
		return count;
	}
	@Transactional
	public int updateRecord(Integer postNo, Integer scriptNo, String recordPath, Integer memberNo) {
		
		int count = 0;

			dao.updateRecord(postNo, scriptNo, recordPath, memberNo);
			count++;

		
		return count;
	}
	@Transactional
	@Override
	public int updateNotDoneRecord(Integer postNo, Integer memberNo2, Integer scriptNo, String recordPath) {
		int count = 0;

			dao.updateRecord(postNo, scriptNo, recordPath, memberNo2);
			count++;
			
		
		return count;
	}
	
	@Transactional
	@Override
	public RecordBean getRecordBean(Integer recordNo) {
		RecordBean rb = null;
		
		rb = dao.getRecordBean(recordNo);
		
		return rb;
	}

	

}
