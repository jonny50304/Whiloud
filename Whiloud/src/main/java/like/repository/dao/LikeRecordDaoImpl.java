package like.repository.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import like.model.LikePostBean;
import like.model.LikeRecordBean;
import like.repository.LikeRecordDao;
import post.model.RecordBean;
import register.model.MemberBean;
@Repository
public class LikeRecordDaoImpl implements LikeRecordDao{
	@Autowired
	SessionFactory factory;
	
	public LikeRecordDaoImpl(){
	
	}
	
	@Override
	public List<LikeRecordBean> getLikeCountByMemberNo(Integer memberNo) {
		List<LikeRecordBean> ikeRecordBeanList = null;
		Session session = factory.getCurrentSession();
		
		String hql ="From LikeRecordBean lrb WHERE lrb.rb.mb.memberNo =:memberNo OR lrb.rb.mb.memberNo =:memberNo";
		ikeRecordBeanList = session.createQuery(hql).setParameter("memberNo", memberNo).list();
		
		return ikeRecordBeanList;
	}

	@Override
	public void likeRecord(MemberBean mb, RecordBean rb) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		LikeRecordBean lrb = new LikeRecordBean(null,mb,rb,ts);
		Session session = factory.getCurrentSession();
		session.save(lrb);
		
	}

	@Override
	public void dislikeRecord(MemberBean mb, RecordBean rb) {
		Session session = factory.getCurrentSession();
		String hql = "DELETE LikeRecordBean lrb WHERE lrb.rb.recordNo =:recordNo AND lrb.mb.memberNo=:memberNo";
		session.createQuery(hql)
			.setParameter("memberNo", mb.getMemberNo())
			.setParameter("recordNo", rb.getRecordNo())
			.executeUpdate();
	}

	@Override
	public List<LikeRecordBean> getLikeListByMemberNo(Integer memberNo) {
		List<LikeRecordBean> likeRecordBeanList = null;
		Session session = factory.getCurrentSession();
		
		String hql ="From LikeRecordBean lrb WHERE lrb.mb.memberNo =:memberNo";
		likeRecordBeanList = session.createQuery(hql).setParameter("memberNo", memberNo).list();
		
		return likeRecordBeanList;
	}


}
