package cn.com.agree.aweb.controller;

import cn.com.agree.aweb.service.login.AuthCenterService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class LoginController {
	
	@Autowired
	private AuthCenterService loginService;

	@Value("password")
	private String grant_type;
	@Value("server")
	private String scope;
	@Value("dashboard")
	private String client_id;
	@Value("123456")
	private String client_secret;
	
	@PostMapping("/oauth/token")
	public Object login(Optional<String> username, Optional<String> password, Optional<String> grant_type, Optional<String> scope, Optional<String> client_id, Optional<String> client_secret) {
		return loginService.login(username.orElseThrow(IllegalArgumentException::new), password.orElseThrow(IllegalArgumentException::new), grant_type.orElse(this.grant_type), scope.orElse(this.scope), client_id.orElse(this.client_id), client_secret.orElse(this.client_secret));
	}
	
}
