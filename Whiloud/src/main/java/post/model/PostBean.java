package post.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import clip.model.ClipBean;
import comment.model.CommentBean;
import like.model.LikePostBean;
import register.model.MemberBean;
@Entity
@Table(name="post")
public class PostBean {
	private Integer postNo;
	private String postTitle;
	private MemberBean mb1;
	private MemberBean mb2;
	private ClipBean cb;
	private Timestamp creationDateTime;
	private String postText;
	private boolean friendOnly ;
	private boolean done;
	private int notDoneRoleNo;
	private boolean isGenderStricted;
	private Set<RecordBean> rb = new LinkedHashSet<>();
	private Set<CommentBean> commentBean = new LinkedHashSet<>();
	private Set<LikePostBean> likePostBean = new LinkedHashSet<>();
	public PostBean(Integer postNo, String postTitle,Timestamp creationDateTime, String postText, boolean friendOnly ,boolean done,int notDoneRoleNo,boolean isGenderStricted) {
		super();
		this.postNo = postNo;
		this.postTitle = postTitle;
		this.creationDateTime = creationDateTime;
		this.postText = postText;
		this.friendOnly = friendOnly;
		this.done = done;
		this.notDoneRoleNo=notDoneRoleNo;
		this.isGenderStricted = isGenderStricted;
	}
	public PostBean() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getPostNo() {
		return postNo;
	}
	public void setPostNo(Integer postNo) {
		this.postNo = postNo;
	}
	@Column(columnDefinition="VARCHAR(20) DEFAULT ' '",name ="postTitle")
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	@Column(columnDefinition="datetime NOT NULL",name ="creationDateTime")
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}
	
	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	@Column(columnDefinition="VARCHAR(255)",name ="postText")
	public String getPostText() {
		return postText;
	}
	
	public void setPostText(String postText) {
		this.postText = postText;
	}
	@Column(columnDefinition="tinyint(1) DEFAULT '0'",name ="friendOnly")
	public boolean getFriendOnly() {
		return friendOnly;
	}
	public void setFriendOnly(boolean friendOnly) {
		this.friendOnly = friendOnly;
	}
	@Column(columnDefinition="tinyint(1) NOT NULL",name ="done")
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	
	@Column(columnDefinition="INTEGER(1) NOT NULL",name ="notDoneRoleNo")
	public int getNotDoneRoleNo() {
		return notDoneRoleNo;
	}
	public void setNotDoneRoleNo(int notDoneRoleNo) {
		this.notDoneRoleNo = notDoneRoleNo;
	}
	@Column(columnDefinition="tinyint(1) default '0'",name ="isGenderStricted")
	public boolean isGenderStricted() {
		return isGenderStricted;
	}
	public void setGenderStricted(boolean isGenderStricted) {
		this.isGenderStricted = isGenderStricted;
	}
	@ManyToOne  
	@JoinColumn(name="memberNo1", nullable=false)  
	public MemberBean getMb1() {
		return mb1;
	}
	public void setMb1(MemberBean mb1) {
		this.mb1 = mb1;
	}
	@ManyToOne  
	@JoinColumn(name="memberNo2", nullable=true)  
	public MemberBean getMb2() {
		return mb2;
	}

	public void setMb2(MemberBean mb2) {
		this.mb2 = mb2;
	}
	
	@ManyToOne 
	@JoinColumn(name="clipNo", nullable=false)  
	public ClipBean getCb() {
		return cb;
	}
	public void setCb(ClipBean cb) {
		this.cb = cb;
	}
	
	@OneToMany(mappedBy="pb", cascade= CascadeType.ALL, orphanRemoval = true)
	public Set<RecordBean> getRb() {
		return rb;
	}
	public void setRb(Set<RecordBean> rb) {
		this.rb = rb;
	}
	
	@OneToMany(mappedBy="pb", cascade= CascadeType.ALL, orphanRemoval = true)
	public Set<CommentBean> getCommentBean() {
		return commentBean;
	}
	public void setCommentBean(Set<CommentBean> commentBean) {
		this.commentBean = commentBean;
	}
	
	@OneToMany(mappedBy="pb", cascade= CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
	public Set<LikePostBean> getLikePostBean() {
		return likePostBean;
	}
	public void setLikePostBean(Set<LikePostBean> likePostBean) {
		this.likePostBean = likePostBean;
	}
	
	
	
	
}
