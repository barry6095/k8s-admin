package cn.com.agree.aweb;

import cn.com.agree.aweb.aop.FilterResultAspect;
import cn.com.agree.aweb.property.AgreeSpringDataExtendProperties;
import cn.com.agree.aweb.springdata.repository.extend.filter.SpringElSqlResultFilter;
import cn.com.agree.aweb.util.JpaFreemarkerTemplateResolver;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhongyi@agree.com.cn
 */
@Configuration
@EnableConfigurationProperties({AgreeSpringDataExtendProperties.class})
public class AgreeBaseRepositoryAutoConfiguration {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AgreeSpringDataExtendProperties properties;

	@Bean
	public JpaFreemarkerTemplateResolver jpaFreemarkerTemplate() throws Exception {

		JpaFreemarkerTemplateResolver templateResolver = new JpaFreemarkerTemplateResolver();
		Optional.ofNullable(properties.getBasePath()).ifPresent(templateResolver::setBasePath);
		Optional.ofNullable(properties.getSuffix()).ifPresent(templateResolver::setSuffix);
		Optional.of(properties.isEnableNativeQuery()).ifPresent(templateResolver::setEnableNativeQuery);
		templateResolver.init();
		logger.info("agree-base-repository loaded");
		return templateResolver;
	}

	@Bean
	public FilterResultAspect filterResultAspect() {
		return new FilterResultAspect();
	}

	@Bean("SpringElSqlResultFilter")
	public SpringElSqlResultFilter springElSqlResultFilter() {
		return new SpringElSqlResultFilter();
	}

}
