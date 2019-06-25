package clip.model;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import post.model.PostBean;
@Entity
@Table(name="clip")
public class ClipBean {
	private Integer clipNo;
	private String clipPath;
	private String clipTitle;
	private String picturePath;
	private Timestamp creationDateTime;
	private String role1;
	private String role1Gender;
	private String role2;
	private String role2Gender;
	private String text;
	private String hashtag;
	private Set<PostBean> pb = new LinkedHashSet<>();
	private Set<ScriptBean>sb = new LinkedHashSet<>();
	public ClipBean(Integer clipNo, String clipPath, String clipTitle, String picturePath, Timestamp creationDateTime,
			String role1,String role1Gender, String role2,String role2Gender, String text,String hashtag) {
		super();
		this.clipNo = clipNo;
		this.clipPath = clipPath;
		this.clipTitle = clipTitle;
		this.picturePath = picturePath;
		this.creationDateTime = creationDateTime;
		this.role1 = role1;
		this.role1Gender = role1Gender;
		this.role2 = role2;
		this.role2Gender = role2Gender; 
		this.text = text;
		this.hashtag = hashtag;
	}
	public ClipBean() {
		
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getClipNo() {
		return clipNo;
	}
	public void setClipNo(Integer clipNo) {
		this.clipNo = clipNo;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) NOT NULL",name ="clipPath")
	public String getClipPath() {
		return clipPath;
	}
	public void setClipPath(String clipPath) {
		this.clipPath = clipPath;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) NOT NULL",name ="clipTitle")
	public String getClipTitle() {
		return clipTitle;
	}
	public void setClipTitle(String clipTitle) {
		this.clipTitle = clipTitle;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) NOT NULL",name ="picturePath")
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	
	@Column(columnDefinition="datetime NOT NULL",name ="creationDateTime")
	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}
	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) NOT NULL",name ="role1")
	public String getRole1() {
		return role1;
	}
	public void setRole1(String role1) {
		this.role1 = role1;
	}
	
	
	@Column(columnDefinition="VARCHAR(10)",name ="role1Gender")
	public String getRole1Gender() {
		return role1Gender;
	}
	public void setRole1Gender(String role1Gender) {
		this.role1Gender = role1Gender;
	}
	
	
	@Column(columnDefinition="VARCHAR(255)",name ="role2")
	public String getRole2() {
		return role2;
	}
	public void setRole2(String role2) {
		this.role2 = role2;
	}
	
	
	@Column(columnDefinition="VARCHAR(10)",name ="role2Gender")
	public String getRole2Gender() {
		return role2Gender;
	}
	public void setRole2Gender(String role2Gender) {
		this.role2Gender = role2Gender;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) ",name ="text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	@Column(columnDefinition="VARCHAR(255) ",name ="hashtag")
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	@OneToMany(mappedBy="cb", cascade= {CascadeType.ALL}, orphanRemoval = false)
	public Set<PostBean> getPb() {
		return pb;
	}
	public void setPb(Set<PostBean> pb) {
		this.pb = pb;
	}
	@OneToMany(mappedBy="cb", cascade= {CascadeType.ALL}, orphanRemoval = false)
	public Set<ScriptBean> getSb() {
		return sb;
	}
	public void setSb(Set<ScriptBean> sb) {
		this.sb = sb;
	}
	
	
}
