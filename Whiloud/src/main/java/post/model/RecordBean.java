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

import clip.model.ScriptBean;
import like.model.LikeRecordBean;
import register.model.MemberBean;
@Entity
@Table(name="record")
public class RecordBean {
	private Integer recordNo;
	private String recordPath;
	private Timestamp creationDateTime;
	private PostBean pb;
	private MemberBean mb;
	private ScriptBean sb;
	
	@JsonIgnore
	private Set<LikeRecordBean> likeRecordBean = new LinkedHashSet<>();
	
	public RecordBean(Integer recordNo, String recordPath, Timestamp creationDateTime, PostBean pb, MemberBean mb,
			ScriptBean sb) {
		super();
		this.recordNo = recordNo;
		this.recordPath = recordPath;
		this.creationDateTime = creationDateTime;
		this.pb = pb;
		this.mb = mb;
		this.sb = sb;
	}
	public RecordBean(Integer recordNo, String recordPath, Timestamp creationDateTime) {
		super();
		this.recordNo = recordNo;
		this.recordPath = recordPath;
		this.creationDateTime = creationDateTime;
	}
	public RecordBean() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRecordNo() {
		return recordNo;
		
	}



	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}


	@Column(columnDefinition="VARCHAR(255) ",name ="recordPath")
	public String getRecordPath() {
		return recordPath;
	}



	public void setRecordPath(String recordPath) {
		this.recordPath = recordPath;
	}


	@Column(columnDefinition="datetime NOT NULL",name ="creationDateTime")
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}



	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}





	@ManyToOne  
	@JoinColumn(name="memberNo", nullable=true)  
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
	@ManyToOne  
	@JoinColumn(name="scriptNo", nullable=false)  
	public ScriptBean getSb() {
		return sb;
	}
	public void setSb(ScriptBean sb) {
		this.sb = sb;
	}
	
	
	@OneToMany(mappedBy="rb", cascade= CascadeType.ALL, orphanRemoval = false, fetch=FetchType.EAGER)
	public Set<LikeRecordBean> getLikeRecordBean() {
		return likeRecordBean;
	}
	public void setLikeRecordBean(Set<LikeRecordBean> likeRecordBean) {
		this.likeRecordBean = likeRecordBean;
	}
}
