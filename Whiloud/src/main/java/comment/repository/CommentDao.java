package comment.repository;

import java.util.List;

import comment.model.CommentBean;
import post.model.PostBean;
import register.model.MemberBean;

public interface CommentDao {

	public List<CommentBean> getCommentByPostNo(Integer postNo);


	public void insertComment(MemberBean mb, PostBean pb, String text, String ip);

}
