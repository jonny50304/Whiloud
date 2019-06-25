package like.repository;

import java.util.List;

import like.model.LikePostBean;
import post.model.PostBean;
import register.model.MemberBean;

public interface LikePostDao {

	public List<LikePostBean> getLikeCountByMemberNo(Integer memberNo);

	public void likePost(MemberBean mb, PostBean pb);

	public Boolean isLiked(Integer memberNo, Integer postNo);

	public Integer getLikeCounts(Integer postNo);

	public void dislikePost(MemberBean mb, PostBean pb);

	public List<LikePostBean> getLikeListByMemberNo(Integer memberNo);

}
