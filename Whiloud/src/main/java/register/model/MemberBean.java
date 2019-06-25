package register.model;

import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import chat.model.ChatBean;
import comment.model.CommentBean;
import friend.model.FriendBean;
import friend.model.FriendRequestBean;
import like.model.LikePostBean;
import like.model.LikeRecordBean;
import post.model.PostBean;
import post.model.RecordBean;
@Entity
@Table(name="member")
public class MemberBean {
	private Integer memberNo;
	private String account ;
	private String PASSWORD;
	private String nickname;
	private String gender;
	private String phone;
	private Timestamp creationDateTime;
	private String picturePath;
	private Date birthday;
	private Integer points;
	private Boolean isFBLogin;
	private Boolean isGoogleLogin;
	private String introduction;
	private String interest;
	private Set<RecordBean> recordBean = new LinkedHashSet<>();
	private Set<PostBean> postBeanMain =  new LinkedHashSet<>();
	private Set<PostBean> postBeanOther =  new LinkedHashSet<>();
	private Set<FriendRequestBean>	iInvite =new LinkedHashSet<>();
	private Set<FriendRequestBean> iWasInvited = new LinkedHashSet<>();
	private Set<FriendBean>	friendBean =new LinkedHashSet<>();
	private Set<FriendBean>	myFriendBean =new LinkedHashSet<>();
	private Set<ChatBean> iSend = new LinkedHashSet<>();
	private Set<ChatBean> othersSend = new LinkedHashSet<>();
	private Set<CommentBean> commentBean = new LinkedHashSet<>();
	private Set<LikePostBean> likePostBean = new LinkedHashSet<>();
	private Set<LikeRecordBean> likeRecordBean = new LinkedHashSet<>();

	public MemberBean(Integer memberNo, String account, String pASSWORD, String nickname, String gender, String phone,
			Timestamp creationDateTime, String picturePath,Date birthday,Integer points, Boolean isFBLogin, Boolean isGoogleLogin,
			String introduction, String interest) {
		super();
		this.memberNo = memberNo;
		this.account = account;
		this.PASSWORD = pASSWORD;
		this.nickname = nickname;
		this.gender = gender;
		this.phone = phone;
		this.creationDateTime = creationDateTime;
		this.picturePath = picturePath;
		this.birthday =birthday;
		this.points = points;
		this.isFBLogin = isFBLogin;
		this.isGoogleLogin = isGoogleLogin;
		this.introduction = introduction;
		this.interest = interest;
	}
	
	public MemberBean() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	@Column(columnDefinition="VARCHAR(100) NOT NULL", name="account",unique=true)
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Column(columnDefinition="VARCHAR(255) NOT NULL", name="PASSWORD")
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	@Column(columnDefinition="VARCHAR(20) NOT NULL", name="nickname",unique=true)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Column(columnDefinition="VARCHAR(10) NOT NULL", name="gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(columnDefinition="VARCHAR(20)",name ="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(columnDefinition="datetime 	NOT NULL",name="creationDateTime")
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	@Column(columnDefinition="VARCHAR(255)", name ="picturePath")
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	@Column(columnDefinition="date", name ="birthday")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Column(columnDefinition="INTEGER(11)",name = "points")
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	@Column(columnDefinition="tinyint(1) NOT NULL",name ="isFBLogin")
	public Boolean getIsFBLogin() {
		return isFBLogin;
	}
	
	public void setIsFBLogin(Boolean isFBLogin) {
		this.isFBLogin = isFBLogin;
	}
	@Column(columnDefinition="tinyint(1) NOT NULL",name = "isGoogleLogin")
	public Boolean getIsGoogleLogin() {
		return isGoogleLogin;
	}
	public void setIsGoogleLogin(Boolean isGoogleLogin) {
		this.isGoogleLogin = isGoogleLogin;
	}
	@Column(columnDefinition="VARCHAR(255) ",name = "introduction")
	public String getIntroduction() {
		return introduction;
	}
	
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Column(columnDefinition="VARCHAR(255) ",name = "interest")
	public String getInterest() {
		return interest;
	}
	
	public void setInterest(String interest) {
		this.interest = interest;
	}
	@OneToMany(mappedBy="mb", cascade= {CascadeType.ALL}, orphanRemoval = false)
	public Set<RecordBean> getRecordBean() {
		return recordBean;
	}

	public void setRecordBean(Set<RecordBean> recordBean) {
		this.recordBean = recordBean;
	}
	@OneToMany(mappedBy="mb1", cascade= {CascadeType.ALL}, orphanRemoval = false)
	public Set<PostBean> getPostBeanMain() {
		return postBeanMain;
	}

	public void setPostBeanMain(Set<PostBean> postBeanMain) {
		this.postBeanMain = postBeanMain;
	}
	@OneToMany(mappedBy="mb2", cascade= {CascadeType.ALL}, orphanRemoval = false)
	public Set<PostBean> getPostBeanOther() {
		return postBeanOther;
	}

	public void setPostBeanOther(Set<PostBean> postBeanOther) {
		this.postBeanOther = postBeanOther;
	}
	
	@OneToMany(mappedBy="memberNo",cascade=CascadeType.ALL, orphanRemoval = true)
	public Set<FriendRequestBean> getiInvite() {
		return iInvite;
	}
	
	public void setiInvite(Set<FriendRequestBean> iInvite) {
		this.iInvite = iInvite;
	}


	@OneToMany(mappedBy="friendNo",cascade=CascadeType.ALL, orphanRemoval = true)
	public Set<FriendRequestBean> getiWasInvited() {
		return iWasInvited;
	}
	
	public void setiWasInvited(Set<FriendRequestBean> iWasInvited) {
		this.iWasInvited = iWasInvited;
	}
	
	@OneToMany(mappedBy="memberNo",cascade=CascadeType.ALL, orphanRemoval = true)
	public Set<FriendBean> getFriendBean() {
		return friendBean;
	}
	public void setFriendBean(Set<FriendBean> friendBean) {
		this.friendBean = friendBean;
	}
	
	@OneToMany(mappedBy="friendNo",cascade=CascadeType.ALL, orphanRemoval = true)
	public Set<FriendBean> getMyFriendBean() {
		return myFriendBean;
	}
	
	public void setMyFriendBean(Set<FriendBean> myFriendBean) {
		this.myFriendBean = myFriendBean;
	}

	@OneToMany(mappedBy="mb1",cascade=CascadeType.ALL, orphanRemoval = true)
	public Set<ChatBean> getiSend() {
		return iSend;
	}

	public void setiSend(Set<ChatBean> iSend) {
		this.iSend = iSend;
	}
	
	@OneToMany(mappedBy="mb2",cascade=CascadeType.ALL, orphanRemoval = true)
	public Set<ChatBean> getOthersSend() {
		return othersSend;
	}
	public void setOthersSend(Set<ChatBean> othersSend) {
		this.othersSend = othersSend;
	}
	
	@OneToMany(mappedBy="mb",cascade=CascadeType.ALL, orphanRemoval = true)
	public Set<CommentBean> getCommentBean() {
		return commentBean;
	}
	public void setCommentBean(Set<CommentBean> commentBean) {
		this.commentBean = commentBean;
	}

	@OneToMany(mappedBy="mb",cascade=CascadeType.ALL, orphanRemoval = true)
	public Set<LikePostBean> getLikePostBean() {
		return likePostBean;
	}
	public void setLikePostBean(Set<LikePostBean> likePostBean) {
		this.likePostBean = likePostBean;
	}

	@OneToMany(mappedBy="mb",cascade=CascadeType.ALL, orphanRemoval = true)
	public Set<LikeRecordBean> getLikeRecordBean() {
		return likeRecordBean;
	}
	public void setLikeRecordBean(Set<LikeRecordBean> likeRecordBean) {
		this.likeRecordBean = likeRecordBean;
	}
	
	
	

}
