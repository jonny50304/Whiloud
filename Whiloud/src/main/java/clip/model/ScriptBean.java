package clip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="script")
public class ScriptBean {
	private Integer scriptNo;
	private Integer roleNo;
	private String roleName;
	private Integer scriptCount;
	private Integer scriptPosition;
	private String startTime;
	private String endTime;
	private String englishScript;
	private String chineseScript;
	private ClipBean cb;
	
	
	public ScriptBean(Integer scriptNo,Integer roleNo, String roleName, Integer scriptCount, Integer scriptPosition,
			String startTime, String endTime, String englishScript, String chineseScript) {
		super();
		this.scriptNo = scriptNo;		
		this.roleNo = roleNo;
		this.roleName = roleName;
		this.scriptCount = scriptCount;
		this.scriptPosition = scriptPosition;
		this.startTime = startTime;
		this.endTime = endTime;
		this.englishScript = englishScript;
		this.chineseScript = chineseScript;
	}
	public ScriptBean() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getScriptNo() {
		return scriptNo;
	}


	public void setScriptNo(Integer scriptNo) {
		this.scriptNo = scriptNo;
	}

	@Column(columnDefinition="INTEGER(1) NOT NULL",name ="roleNo")
	public int getRoleNo() {
		return roleNo;
	}


	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
	}

	@Column(columnDefinition="VARCHAR(20) NOT NULL",name ="roleName")
	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(columnDefinition="INTEGER(11) NOT NULL",name ="scriptCount")
	public int getScriptCount() {
		return scriptCount;
	}


	public void setScriptCount(Integer scriptCount) {
		this.scriptCount = scriptCount;
	}

	@Column(columnDefinition="INTEGER(11) NOT NULL",name ="scriptPosition")
	public int getScriptPosition() {
		return scriptPosition;
	}


	public void setScriptPosition(Integer scriptPosition) {
		this.scriptPosition = scriptPosition;
	}

	@Column(columnDefinition="VARCHAR(10) NOT NULL",name ="startTime")
	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(columnDefinition="VARCHAR(10) NOT NULL",name ="endTime")
	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(columnDefinition="VARCHAR(255) NOT NULL",name ="englishScript")
	public String getEnglishScript() {
		return englishScript;
	}


	public void setEnglishScript(String englishScript) {
		this.englishScript = englishScript;
	}

	@Column(columnDefinition="VARCHAR(255) NOT NULL",name ="chineseScript")
	public String getChineseScript() {
		return chineseScript;
	}


	public void setChineseScript(String chineseScript) {
		this.chineseScript = chineseScript;
	}
	@ManyToOne 
	@JoinColumn(name="clipNo", nullable=true)  
	public ClipBean getCb() {
		return cb;
	}

	public void setCb(ClipBean cb) {
		this.cb = cb;
	}
	
	
	
	
}
