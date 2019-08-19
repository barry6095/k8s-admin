package cn.com.agree.aweb.springdata.repository.extend.query;

import cn.com.agree.aweb.annotation.QueryParam;
import cn.com.agree.aweb.annotation.TemplateQuery;
import cn.com.agree.aweb.util.JpaFreemarkerTemplateResolver;
import freemarker.template.TemplateException;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;

/**
 * @author zhongyi@agree.com.cn
 */
public class FreemarkerTemplateQuery extends AbstractJpaQuery {

    private TemplateQuery annotation;
    private EntityManager em;

    /**
     * Creates a new {@link AbstractJpaQuery} from the given {@link JpaQueryMethod}.
     *
     * @param method jpa query method
     * @param em     entity manager
     */
    public FreemarkerTemplateQuery(JpaQueryMethod method, EntityManager em, TemplateQuery annotation) {
        super(method, em);
        this.em = em;
        this.annotation = annotation;
    }

    @Override
    protected Query doCreateQuery(Object[] values) {
    	Object params = Stream.of(values).findFirst().orElseThrow(() -> new IllegalArgumentException("模板查询需要传入查询对象"));
    	String queryString = getQueryString(params);
		return createQuery(queryString, params);
    }

	private String getQueryString(Object params) {
		String queryString;
		try {
			queryString = JpaFreemarkerTemplateResolver.getQueryString(annotation.key(), params);
		} catch (TemplateException | IOException e1) {
			throw new IllegalStateException("解析模板[" + annotation.key() + "]失败:", e1);
		}
		return queryString;
	}

	private Query createQuery(String queryString, Object params) {
		Query query;
		if(annotation.isNativeQuery()) {
			query = em.createNativeQuery(queryString);
		}else {
			query = em.createQuery(queryString);
		}

		if(annotation.isPreparedQuery()) {
			if(params instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) params;
				for(Object k : map.keySet()) {
					query.setParameter(k.toString(), map.get(k));
				}
			}else {
				Field[] fields = params.getClass().getDeclaredFields();
				try {
					for(Field field : fields) {
						QueryParam annotation = field.getAnnotation(QueryParam.class);
						if(annotation != null) {
							if (containsParameter(query, annotation.name())) {
								query.setParameter(annotation.name(), new PropertyDescriptor(field.getName(), params.getClass()).getReadMethod().invoke(params));
							} else if (containsParameter(query, field.getName())) {
								query.setParameter(field.getName(), new PropertyDescriptor(field.getName(), params.getClass()).getReadMethod().invoke(params));
							}
						}
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| IntrospectionException e) {
					e.printStackTrace();
				}
			}
		}
        return query;
	}



    @Override
    @SuppressWarnings("unchecked")
    protected TypedQuery<Long> doCreateCountQuery(Object[] values) {
		Object params = Stream.of(values).findFirst().orElseThrow(() -> new IllegalArgumentException("模板查询需要传入查询对象"));
		String queryString = getQueryString(params);
		queryString = queryString.replaceFirst("((SELECT|select +)?(.*)? +)?((FROM|from).*)", "SELECT COUNT(*) $3");
		return (TypedQuery<Long>) this.createQuery(queryString, params);
	}

	private boolean containsParameter(Query query, String paramName) {
		return query.getParameters().stream().anyMatch(item -> item.getName().equals(paramName));
	}
}
