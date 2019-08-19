package cn.com.agree.aweb.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@Configuration
@Slf4j
public class ListenerConfiguration {

	@EventListener
	public void authSuccessEventListener(AuthenticationSuccessEvent authorizedEvent) {
		// write custom code here for login success audit
		log.info("User Oauth2 login success");
		log.info("This is success event : " + authorizedEvent.getAuthentication().getPrincipal());
	}

	@EventListener
	public void authFailedEventListener(AbstractAuthenticationFailureEvent oAuth2AuthenticationFailureEvent) {
		// write custom code here login failed audit.
		log.info("User Oauth2 login Failed");
		log.info("This is failure event : " + oAuth2AuthenticationFailureEvent.getAuthentication().getPrincipal());
	}
}
