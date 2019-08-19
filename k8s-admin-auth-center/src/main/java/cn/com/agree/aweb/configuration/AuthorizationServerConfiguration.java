package cn.com.agree.aweb.configuration;

import cn.com.agree.aweb.custom.impl.AgreeRedisTokenStore;
import cn.com.agree.aweb.service.user.UserServiceImpl;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	RedisConnectionFactory redisConnectionFactory;

	// ################# 以下配置jdbc ############

//	@Autowired
//	private DataSource dataSource;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserServiceImpl userServiceDetail;

//	@Autowired
//	private ClientDetailsService clientDetailsService;

	static final Logger logger = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);

	@Bean
	public TokenStore tokenStore() {
//		return new JdbcTokenStore(dataSource);
		return new AgreeRedisTokenStore(redisConnectionFactory);
	}

//	@Bean // 声明 ClientDetails实现
//	public ClientDetailsService clientDetailsService() {
//		return new JdbcClientDetailsService(dataSource);
//	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");

		logger.info("finalSecret === " + finalSecret);

		// 配置两个客户端，一个用于password认证一个用于client认证
        clients.inMemory().withClient("api-gateway")
                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                .scopes("server")
                .authorities("oauth2")
                .secret(finalSecret)
                .and()
                .withClient("dashboard")
                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                .scopes("server")
                .authorities("oauth2")
                .secret(finalSecret)
                .and()
                .withClient("activiti-core")
                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                .scopes("server")
                .authorities("oauth2")
                .secret(finalSecret)
                .and()
                .withClient("api-gateway")
                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                .scopes("server")
                .authorities("oauth2")
                .secret(finalSecret)
                .and()
                .withClient("k8s-admin")
                .authorizedGrantTypes("client_credentials", "refresh_token", "password")
                .scopes("server")
                .authorities("oauth2")
                .secret(finalSecret);

//		clients.withClientDetails(clientDetailsService);

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// redisTokenStore
//        endpoints.tokenStore(new MyRedisTokenStore(redisConnectionFactory))
//                .authenticationManager(authenticationManager)
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);

		// 存数据库
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
				.userDetailsService(userServiceDetail);

		// 配置tokenServices参数
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setTokenStore(endpoints.getTokenStore());
		tokenServices.setSupportRefreshToken(false);
		tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
		tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
		tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
		endpoints.tokenServices(tokenServices);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// 允许表单认证
		security.allowFormAuthenticationForClients().tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}
}