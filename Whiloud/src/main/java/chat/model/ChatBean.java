package chat.model;

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
@Table(name="chat")
public class ChatBean {
	private Integer chatNo;
	private MemberBean mb1;
	private MemberBean mb2;
	private Timestamp creationDateTime;
	private String message;
	private boolean isRead;
	public ChatBean(Integer chatNo, Timestamp creationDateTime, String message,
			boolean isRead) {
		super();
		this.chatNo = chatNo;
		this.creationDateTime = creationDateTime;
		this.message = message;
		this.isRead = isRead;
	}
	public ChatBean() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getChatNo() {
		return chatNo;
	}
	public void setChatNo(Integer chatNo) {
		this.chatNo = chatNo;
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
	@JoinColumn(name="memberNo2", nullable=false)  
	public MemberBean getMb2() {
		return mb2;
	}
	public void setMb2(MemberBean mb2) {
		this.mb2 = mb2;
	}
	@Column(columnDefinition="datetime NOT NULL",name ="creationDateTime")
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	@Column(columnDefinition="VARCHAR(255) NOT NULL DEFAULT ''",name ="message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Column(columnDefinition="tinyint(1) NOT NULL DEFAULT '0'",name ="isRead")
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}


	
	
	
	
}
