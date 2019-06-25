package post.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import init.HibernateUtils;
import post.model.PostBean;
import post.repository.PostDao;

@Repository
public class PostDaoImpl_Hibernate implements PostDao{
	@Autowired
	SessionFactory factory;

	public PostDaoImpl_Hibernate() {
		
	
	}
	
	public PostBean getPostDetailBean(String postNo){
		PostBean pb = null;
		Integer PostNo = Integer.valueOf(postNo);
		Session session = factory.getCurrentSession();
		String hql = "From PostBean pb Where pb.postNo =:postNo";
		pb =  (PostBean) session.createQuery(hql).setParameter("postNo", PostNo).uniqueResult();
		return pb;
	}
	
	@SuppressWarnings("unchecked")
	public List<PostBean> getAllPosts(){
		List<PostBean> postList = null;

		Session session = factory.getCurrentSession();
		String hql = "From PostBean pb Order by pb.creationDateTime desc";
		postList =  session.createQuery(hql).list();
		return postList;
	}

	@Override
	public int savePost(PostBean pb) {
		int n = 0 ;
		Session session = factory.getCurrentSession();
		session.save(pb);
		n++;
		return n;
	}

	@Override
	public int updatePostMemberNo2(Integer postNo, Integer memberNo2) {
		int n = 0 ;
		Session session = factory.getCurrentSession();
		
		String hql="UPDATE PostBean pb "
				+ " SET pb.mb2.memberNo =:memberNo2, pb.done = 1, pb.notDoneRoleNo = 0, pb.creationDateTime = CURRENT_TIMESTAMP() "
				+ " WHERE pb.postNo =:postNo";
		Query query = session.createQuery(hql);
		query.setParameter("postNo", postNo);
		query.setParameter("memberNo2", memberNo2);
		n = query.executeUpdate();
		
		return n;
	}
	
	@Override
	public List<PostBean> getAllDonePosts(){
		List<PostBean> postList = null;

		Session session = factory.getCurrentSession();
		String hql = "From PostBean WHERE done = 1  ORDER BY creationDateTime DESC";
		postList =  session.createQuery(hql).list();
		return postList;
	}

	@Override
	public List<PostBean> getAllCooperation() {
		List<PostBean> postList = null;

		Session session = factory.getCurrentSession();
		String hql = "From PostBean WHERE done = 0  ORDER BY creationDateTime DESC";
		postList =  session.createQuery(hql).list();
		return postList;
	}

	@Override
	public List<PostBean> getPostsByMemberNo(int memberNo) {
		List<PostBean> postList = null;

		Session session = factory.getCurrentSession();
		String hql = "From PostBean WHERE memberNo1 =:memberNo1 OR memberNo2 =:memberNo2 ORDER BY creationDateTime DESC";
		postList =  session.createQuery(hql)
				.setParameter("memberNo1", memberNo)
				.setParameter("memberNo2", memberNo)
				.list();
		return postList;
	}

//	@Override
//	public void deletePost(Integer postNo, Integer memberNo) {
//		Session session = factory.getCurrentSession();
//		String hql = "DELETE from PostBean pb WHERE pb.postNo=:postNo AND pb.mb1.memberNo =:memberNo";
//		session.createQuery(hql)
//			.setParameter("postNo", postNo)
//			.setParameter("memberNo", memberNo)
//			.executeUpdate();
//       
//	}

	@Override
	public void deletePost(PostBean pb) {
		Session session = factory.getCurrentSession();
		session.delete(pb);
	}
}
