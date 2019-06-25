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
@Table(name="friend")
public class FriendBean {
	private Integer serialNo;
	private MemberBean memberNo ;
	private MemberBean friendNo;;
	private Timestamp creationDateTime;
	private Integer unread;
	
	public FriendBean() {
		
	}
	
	

	public FriendBean(Integer serialNo, MemberBean memberNo, MemberBean friendNo, Timestamp creationDateTime,
			Integer unread) {
		super();
		this.serialNo = serialNo;
		this.memberNo = memberNo;
		this.friendNo = friendNo;
		this.creationDateTime = creationDateTime;
		this.unread = unread;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
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


	@Column(columnDefinition="int",name ="unread")
	public Integer getUnread() {
		return unread;
	}



	public void setUnread(Integer unread) {
		this.unread = unread;
	}
	
	

}
