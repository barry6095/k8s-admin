package cn.com.agree.aweb.springdata.repository.extend.query;

import cn.com.agree.aweb.annotation.TemplateQuery;
import cn.com.agree.aweb.util.AopTargetUtils;
import cn.com.agree.aweb.util.JpaFreemarkerTemplateResolver;
import cn.com.agree.aweb.util.QueryBuilder;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.data.jpa.repository.query.JpaParameters;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.repository.query.Parameter;
import org.springframework.data.repository.query.ParameterAccessor;
import org.springframework.data.repository.query.ParametersParameterAccessor;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:stormning@163.com">stormning</a>
 * @version V1.0, 2015/8/9.
 */
public class FreemarkerJpaQuery extends AbstractJpaQuery {

    private TemplateQuery annotation;

    public FreemarkerJpaQuery(JpaQueryMethod method, EntityManager em, TemplateQuery annotation) {
        super(method, em);
        this.annotation = annotation;
    }

    @SuppressWarnings("deprecation")
	@Override
    protected Query doCreateQuery(@NonNull Object[] values) {
        String nativeQuery = getQuery(values);
        JpaParameters parameters = getQueryMethod().getParameters();
        ParameterAccessor accessor = new ParametersParameterAccessor(parameters, values);
        String sortedQueryString = QueryUtils
                .applySorting(nativeQuery, accessor.getSort(), QueryUtils.detectAlias(nativeQuery));
        Query query = bind(createJpaQuery(sortedQueryString), values);
        if (parameters.hasPageableParameter()) {
            Pageable pageable = (Pageable) (values[parameters.getPageableIndex()]);
            if (pageable != null) {
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
        }
        return query;
    }

    private String getQuery(Object[] values) {
        return getQueryFromTpl(values);
    }

    private String getQueryFromTpl(Object[] values) {
        try {
            return JpaFreemarkerTemplateResolver
                .getQueryString(annotation.key(), Stream.of(values).findFirst().orElseThrow(() -> new IllegalArgumentException("模板查询需要传入查询对象")));
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, Object> getParams(Object[] values) {
        JpaParameters parameters = getQueryMethod().getParameters();
        //gen model
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < parameters.getNumberOfParameters(); i++) {
            Object value = values[i];
            Parameter parameter = parameters.getParameter(i);
            if (value != null && canBindParameter(parameter)) {
                if (!QueryBuilder.isValidValue(value)) {
                    continue;
                }
                Class<?> clz = value.getClass();
                if (clz.isPrimitive() || String.class.isAssignableFrom(clz) || Number.class.isAssignableFrom(clz)
                        || clz.isArray() || Collection.class.isAssignableFrom(clz) || clz.isEnum()) {
                    params.put(parameter.getName().orElse(null), value);
                } else {
                    params = QueryBuilder.toParams(value);
                }
            }
        }
        return params;
    }

    private Query createJpaQuery(String queryString) {
        Class<?> objectType = getQueryMethod().getReturnedObjectType();

        //get original proxy query.
        Query oriProxyQuery;
        //must be hibernate QueryImpl
//        Query query = null;

        if (annotation.isNativeQuery() && getQueryMethod().isQueryForEntity()) {
            oriProxyQuery = getEntityManager().createNativeQuery(queryString, objectType);
        } else {
            oriProxyQuery = getEntityManager().createQuery(queryString, objectType);
//            query = AopTargetUtils.getTarget(oriProxyQuery);
//            Class<?> genericType;
            //find generic type
//            if (objectType.isAssignableFrom(Map.class)) {
//                genericType = objectType;
//            } else {
//                ClassTypeInformation<?> ctif = ClassTypeInformation.from(objectType);
//                TypeInformation<?> actualType = ctif.getActualType();
//                genericType = actualType.getType();
//            }
//            if (genericType != Void.class) {
//                QueryBuilder.transform(query, genericType);
//            }
        }
        //return the original proxy query, for a series of JPA actions, e.g.:close em.
        return oriProxyQuery;
    }

//    private String getEntityName() {
//        return getQueryMethod().getEntityInformation().getJavaType().getSimpleName();
//    }
//
//    private String getMethodName() {
//        return getQueryMethod().getName();
//    }

    @Override
    @SuppressWarnings("unchecked")
    protected TypedQuery<Long> doCreateCountQuery(Object[] values) {
        TypedQuery<Long> query = (TypedQuery<Long>) getEntityManager()
                .createQuery(QueryBuilder.toCountQuery(getQuery(values)));
        bind(query, values);
        return query;
    }

    private Query bind(Query query, Object[] values) {
        //get proxy target if exist.
        //must be hibernate QueryImpl
        Query targetQuery = AopTargetUtils.getTarget(query);
        Map<String, Object> params = getParams(values);
        if (!CollectionUtils.isEmpty(params)) {
            QueryBuilder.setParams(targetQuery, params);
        }
        return query;
    }

    private boolean canBindParameter(Parameter parameter) {
        return parameter.isBindable();
    }
}