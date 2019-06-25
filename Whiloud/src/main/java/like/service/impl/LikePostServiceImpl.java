package like.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import like.model.LikePostBean;
import like.repository.LikePostDao;
import like.service.LikePostService;
import post.model.PostBean;
import register.model.MemberBean;
import register.repository.MemberDao;

@Service
public class LikePostServiceImpl implements LikePostService {
	@Autowired
	LikePostDao dao;
	
	@Override
	@Transactional
	public List<LikePostBean> getLikeCountByMemberNo(Integer memberNo) {
		List<LikePostBean> lpb =null;

		lpb = dao.getLikeCountByMemberNo(memberNo);

     return lpb;
	}

	@Override
	@Transactional
	public void likePost(MemberBean mb, PostBean pb) {
		dao.likePost(mb, pb);
		
	}
	
	@Transactional
	@Override
	public Boolean isLiked(Integer memberNo, Integer postNo) {
		return dao.isLiked(memberNo,postNo);
	}
	
	@Transactional
	@Override
	public Integer getLikeCounts(Integer postNo) {
		return dao.getLikeCounts(postNo);
	}
	
	@Transactional
	@Override
	public void dislikePost(MemberBean mb, PostBean pb) {
		dao.dislikePost(mb, pb);
		
	}
	
	@Transactional
	@Override
	public List<LikePostBean> getLikeListByMemberNo(Integer memberNo) {
		List<LikePostBean> lpb = null;
		lpb = dao.getLikeListByMemberNo(memberNo);
		return lpb;
	}

}
