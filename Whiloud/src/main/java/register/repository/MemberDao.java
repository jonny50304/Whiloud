package register.repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import register.model.MemberBean;

public interface MemberDao {
	public boolean idExists(String id);
	
	public boolean  nicknameExists(String nickname);
	
	public int saveMember(MemberBean mb) ;
	
	public MemberBean queryMember(String id);
	
	public  MemberBean getMember(String id);
	
	public List<MemberBean> getAllMember();
	
	public MemberBean checkIDPassword(String account, String PASSWORD);	
	
	public void setConnection(Connection con);

	public List<MemberBean> getAllMemberByCreationDateTime();

	public void savePicture(Integer memberNo, String picturePath);

	public void updateInformation(MemberBean mb);

	public void updatePassword(Integer memberNo, String password);

	public MemberBean checkFbId(String account);
}
