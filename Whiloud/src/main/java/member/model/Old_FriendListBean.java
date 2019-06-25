package member.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import register.model.MemberBean;
@Entity
@Table(name="friendlist")
public class Old_FriendListBean {
	private Integer serialNo;
	private Timestamp creationDateTime;
	private Integer accepted;
	private Integer chatNo;
	private MemberBean memberNo ;
	private MemberBean friendNo;
	public Old_FriendListBean(Integer serialNo, Timestamp creationDateTime, Integer accepted, Integer chatNo) {
		super();
		this.serialNo = serialNo;
		this.creationDateTime = creationDateTime;
		this.accepted = accepted;
		this.chatNo = chatNo;
	}
	
	public Old_FriendListBean() {
	
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	
	@Column(columnDefinition="datetime NOT NULL",name ="creationDateTime")
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	@Column(columnDefinition="tinyint(1) NOT NULL",name ="accepted")
	public int getAccepted() {
		return accepted;
	}
	public void setAccepted(Integer accepted) {
		this.accepted = accepted;
	}
	@Column(columnDefinition="Integer(11)",name ="chatNo")
	public Integer getChatNo() {
		return chatNo;
	}
	public void setChatNo(Integer chatNo) {
		this.chatNo = chatNo;
	}
	
	@ManyToOne  
	@JoinColumn(name="memberNo", nullable=false)  
	public MemberBean getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(MemberBean membernNo) {
		this.memberNo = membernNo;
	}
	
	@ManyToOne  
	@JoinColumn(name="friendNo", nullable=false)  
	public MemberBean getFriendNo() {
		return friendNo;
	}
	public void setFriendNo(MemberBean friendNo) {
		this.friendNo = friendNo;
	}
	
	
}
