package like.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import post.model.PostBean;
import register.model.MemberBean;

@Entity
@Table(name="likepost")
public class LikePostBean {
	private Integer likePostNo;
	private MemberBean mb;
	private PostBean pb;
	private Timestamp creationDateTime;
	
	public LikePostBean(){
		
	}

	public LikePostBean(Integer likePostNo, MemberBean mb, PostBean pb, Timestamp creationDateTime) {
		super();
		this.likePostNo = likePostNo;
		this.mb = mb;
		this.pb = pb;
		this.creationDateTime = creationDateTime;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getLikePostNo() {
		return likePostNo;
	}
	public void setLikePostNo(Integer likePostNo) {
		this.likePostNo = likePostNo;
	}
	
	@ManyToOne
	@JoinColumn(name="memberNo", nullable=false)
	public MemberBean getMb() {
		return mb;
	}
	public void setMb(MemberBean mb) {
		this.mb = mb;
	}
	
	@ManyToOne
	@JoinColumn(name="postNo", nullable=false)
	public PostBean getPb() {
		return pb;
	}
	public void setPb(PostBean pb) {
		this.pb = pb;
	}
	
	
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
	
	
}
