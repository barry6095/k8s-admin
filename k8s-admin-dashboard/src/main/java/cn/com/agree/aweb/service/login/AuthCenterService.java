package cn.com.agree.aweb.service.login;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "k8s-admin-auth-center", fallbackFactory = LoginExeceptionHandler.class)
@Service
public interface AuthCenterService {
	
	@PostMapping(value = "/oauth/token")
	String login(@RequestParam("username") String username, @RequestParam("password") String password,
      @RequestParam("grant_type") String grant_type, @RequestParam("scope") String scope,
      @RequestParam("client_id") String client_id,
      @RequestParam("client_secret") String client_secret);
}
