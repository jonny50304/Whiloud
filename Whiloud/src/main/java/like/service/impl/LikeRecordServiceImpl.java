package like.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import like.model.LikePostBean;
import like.model.LikeRecordBean;
import like.repository.LikePostDao;
import like.repository.LikeRecordDao;
import like.service.LikeRecordService;
import post.model.RecordBean;
import register.model.MemberBean;

@Service
public class LikeRecordServiceImpl implements LikeRecordService {
	@Autowired
	LikeRecordDao dao;
	
	@Override
	@Transactional
	public List<LikeRecordBean> getLikeCountByMemberNo(Integer memberNo) {
		List<LikeRecordBean> lrb =null;

		lrb = dao.getLikeCountByMemberNo(memberNo);

     return lrb;
	}

	@Override
	@Transactional
	public void likeRecord(MemberBean mb, RecordBean rb) {
		dao.likeRecord(mb,rb);
	}
	
	@Transactional
	@Override
	public void dislikeRecord(MemberBean mb, RecordBean rb) {
		dao.dislikeRecord(mb,rb);
	}
	
	@Transactional
	@Override
	public List<LikeRecordBean> getLikeListByMemberNo(Integer memberNo) {
		List<LikeRecordBean> lrb =null;

		lrb = dao.getLikeListByMemberNo(memberNo);

		return lrb;
	}

}
