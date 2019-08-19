package cn.com.agree.aweb.annotation;

import cn.com.agree.aweb.AgreeBaseRepositoryAutoConfiguration;
import cn.com.agree.aweb.springdata.repository.extend.AgreeJpaRepositoryFactoryBean;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AgreeBaseRepositoryAutoConfiguration.class)
@EnableAspectJAutoProxy
@EnableJpaRepositories(repositoryFactoryBeanClass = AgreeJpaRepositoryFactoryBean.class)
@EnableJpaAuditing
public @interface EnableAgreeBaseRepository {

}
