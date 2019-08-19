package cn.com.agree.aweb;

import cn.com.agree.aweb.annotation.EnableAgreeBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackages = "cn.com.agree.aweb.entity.po.auth.center")
@EnableFeignClients
@EnableAgreeBaseRepository
public class AuthenticationApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
