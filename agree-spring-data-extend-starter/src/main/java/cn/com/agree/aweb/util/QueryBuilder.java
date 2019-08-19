package cn.com.agree.aweb.util;

import cn.com.agree.aweb.annotation.QueryParam;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhongyi@agree.com.cn
 */
public class QueryBuilder {

    private static final Pattern ORDER_BY_PATTERN = Pattern
            .compile("order\\s+by.+?$", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    private static String wrapCountQuery(String query) {
        query = query.replaceFirst("((SELECT|select +)?(.*)? +)?((FROM|from).*)", "SELECT COUNT(*) $4");
        return query;
    }

    private static String cleanOrderBy(String query) {
        Matcher matcher = ORDER_BY_PATTERN.matcher(query);
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (matcher.find()) {
            String part = matcher.group(i);
            if (canClean(part)) {
                matcher.appendReplacement(sb, "");
            } else {
                matcher.appendReplacement(sb, part);
            }
            i++;
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static boolean canClean(String orderByPart) {
        return orderByPart != null && (!orderByPart.contains(")")
                ||
                StringUtils.countOccurrencesOf(orderByPart, ")") == StringUtils
                    .countOccurrencesOf(orderByPart, "("));
    }

    public static String toCountQuery(String query) {
        return wrapCountQuery(cleanOrderBy(query));
    }

    public static void setParams(Query query, Object beanOrMap) {
        Map<String, Object> params = toParams(beanOrMap);
        params.forEach((k, v) -> {
            if (containsParameter(query, k)) {
                query.setParameter(k, v);
            }
        });
    }

    private static boolean containsParameter(Query query, String paramName) {
        return query.getParameters().stream().anyMatch(item -> item.getName().equals(paramName));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toParams(Object beanOrMap) {
        Map<String, Object> params;
        if (beanOrMap instanceof Map) {
            params = (Map<String, Object>) beanOrMap;
        } else {
            params = toMap(beanOrMap);
        }
        if (!CollectionUtils.isEmpty(params)) {
            params.keySet().removeIf(key -> !isValidValue(params.get(key)));
        }
        return params;
    }

    public static boolean isValidValue(Object object) {
        if (object == null) {
            return false;
        }
        /*if (object instanceof Number && ((Number) object).longValue() == 0) {
            return false;
		}*/
        return !(object instanceof Collection && CollectionUtils.isEmpty((Collection<?>) object));
    }

    private static Map<String, Object> toMap(Object bean) {
        if (bean == null) {
            return Collections.emptyMap();
        }
        try {
            Map<String, Object> description = new HashMap<>(10);
            PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(bean.getClass());
            for (PropertyDescriptor descriptor : descriptors) {
                if ("class".equals(descriptor.getName())) {
                    continue;
                }
                if (descriptor.getReadMethod() == null) {
                    continue;
                }
                String name = descriptor.getName();
                QueryParam annotation = bean.getClass().getDeclaredField(name).getAnnotation(QueryParam.class);
                if (annotation != null && !StringUtils.isEmpty(annotation.name())) {
                    description.put(annotation.name(), descriptor.getReadMethod().invoke(bean));
                } else {
                    description.put(name, descriptor.getReadMethod().invoke(bean));
                }
            }
            return description;
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }
}