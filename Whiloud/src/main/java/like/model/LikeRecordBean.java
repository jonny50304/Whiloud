package like.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import post.model.RecordBean;
import register.model.MemberBean;

@Entity
@Table(name="likerecord")
public class LikeRecordBean {
	private Integer likeRecordNo;
	private MemberBean mb;
	private RecordBean rb;
	private Timestamp creationDateTime;
	
	public LikeRecordBean() {
		
	}

	public LikeRecordBean(Integer likeRecordNo, MemberBean mb, RecordBean rb, Timestamp creationDateTime) {
		super();
		this.likeRecordNo = likeRecordNo;
		this.mb = mb;
		this.rb = rb;
		this.creationDateTime = creationDateTime;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getLikeRecordNo() {
		return likeRecordNo;
	}
	public void setLikeRecordNo(Integer likeRecordNo) {
		this.likeRecordNo = likeRecordNo;
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
	@JoinColumn(name="recordNo", nullable=false)
	public RecordBean getRb() {
		return rb;
	}
	public void setRb(RecordBean rb) {
		this.rb = rb;
	}

	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	
}
