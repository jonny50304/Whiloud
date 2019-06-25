package post.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import init.HibernateUtils;
import post.model.PostBean;
import post.repository.PostDao;
import post.repository.impl.PostDaoImpl_Hibernate;
import post.service.PostService;
@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostDao dao ;
	
	public PostServiceImpl() {

	}
	@Transactional
	@Override
	public PostBean getPostDetailBean(String postNo) {
		PostBean pb  = null;

			pb =dao.getPostDetailBean(postNo);

		return pb;
	}
	@Transactional
	@Override
	public List<PostBean> getAllPosts() {
		List<PostBean> postList = null;

			postList =dao.getAllPosts();
			Hibernate.initialize(postList);

		return postList;
	}
	
	@Transactional
	@Override
	public List<PostBean> getAllDonePosts() {
		List<PostBean> postList = null;

			postList =dao.getAllDonePosts();
			Hibernate.initialize(postList);

		return postList;
	}
	
	@Transactional
	@Override
	public int savePost(PostBean pb) {
		int count = 0;

			dao.savePost(pb);
			count++;

		
		return count;
		
	}
	@Transactional
	@Override
	public int updatePostMemberNo2(Integer postNo, Integer memberNo2) {
		int count = 0;

			dao.updatePostMemberNo2(postNo, memberNo2);
			count++;
		
		return count;
	}
	
	@Transactional
	@Override
	public List<PostBean> getAllCooperation() {
		List<PostBean> postList = null;

		postList =dao.getAllCooperation();
		Hibernate.initialize(postList);

		return postList;

	}
	
	@Transactional
	@Override
	public List<PostBean> getPostsByMemberNo(int memberNo) {
		List<PostBean> postList = null;

		postList =dao.getPostsByMemberNo(memberNo);
		//Hibernate.initialize(postList);

		return postList;
	}
	
	@Transactional
	@Override
	public void deletePost(PostBean pb) {
		dao.deletePost(pb);
		
	}
	
//	@Transactional
//	@Override
//	public void deletePost(Integer postNo, Integer memberNo) {
//		dao.deletePost(postNo, memberNo);
//	}

	


}
