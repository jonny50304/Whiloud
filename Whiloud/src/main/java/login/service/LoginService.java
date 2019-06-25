package login.service;

import register.model.MemberBean;

// 定義進行登入時系統必須執行的功能
public interface LoginService {
	public MemberBean checkIDPassword(String account, String PASSWORD) ;

	public MemberBean checkFbId(String account);
}
