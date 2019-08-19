package cn.com.agree.aweb;

import cn.com.agree.aweb.annotation.EnableAgreeBaseRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAgreeBaseRepository
@EnableFeignClients
@EntityScan(basePackages = "cn.com.agree.aweb.entity.po.system")
public class SystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
	}

}
