package cn.com.agree.aweb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.data.annotation.QueryAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@QueryAnnotation
@Documented
public @interface TemplateQuery {
	String key();
	boolean isNativeQuery() default false;
	boolean isPreparedQuery() default true;
}
