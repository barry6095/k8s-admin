package cn.com.agree.aweb.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhongyi@agree.com.cn
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext springContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.springContext = applicationContext;
    }

    public static ApplicationContext getSpringContext() {
        return SpringContextUtil.springContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return getSpringContext().getBean(clazz);
    }
}