package register.service;

import java.util.List;
import java.util.Map;

import register.model.MemberBean;

public interface MemberService {
	boolean idExists(String id);
	
	boolean nicknameExists(String nickname);
	
	int saveMember(MemberBean mb);
	
	public MemberBean queryMember(String id);
	
	public MemberBean checkIdPassword(String account, String PASSWORD);
	
	public  MemberBean getMember(String id);
	
	public List<MemberBean> getAllMember();

	public List<MemberBean> getAllMemberByCreationDateTime();

	void savePicture(Integer memberNo, String picturePath);

	int updateInformation(MemberBean mb);

	void updatePassword(Integer memberNo, String password);
}
