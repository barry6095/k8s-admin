package cn.com.agree.aweb.service.login;

public class LoginExeceptionHandler implements AuthCenterService{

	@Override
	public String login(String username, String password, String grant_type, String scope, String client_id,
			String client_secret) {
		return "认证中心服务不可用";
	}

}
