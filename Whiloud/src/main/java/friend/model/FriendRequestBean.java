package friend.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import register.model.MemberBean;

@Entity
@Table(name="friendRequest")
public class FriendRequestBean {
	private Integer requestNo;
	private MemberBean memberNo ;
	private MemberBean friendNo;;
	private Timestamp creationDateTime;
	
	public FriendRequestBean() {
		
	}
	
	
	
	public FriendRequestBean(Integer requestNo, MemberBean memberNo, MemberBean friendNo, Timestamp creationDateTime) {
		super();
		this.requestNo = requestNo;
		this.memberNo = memberNo;
		this.friendNo = friendNo;
		this.creationDateTime = creationDateTime;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(Integer requestNo) {
		this.requestNo = requestNo;
	}
	
	
	@ManyToOne  
	@JoinColumn(name="memberNo", nullable=false)  
	public MemberBean getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(MemberBean memberNo) {
		this.memberNo = memberNo;
	}
	
	@ManyToOne  
	@JoinColumn(name="friendNo", nullable=false)  
	public MemberBean getFriendNo() {
		return friendNo;
	}

	public void setFriendNo(MemberBean friendNo) {
		this.friendNo = friendNo;
	}
	
	@Column(columnDefinition="datetime NOT NULL",name ="creationDateTime")
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
}
