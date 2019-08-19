package cn.com.agree.aweb.interceptor;

import cn.com.agree.aweb.springdata.repository.extend.filter.ResultFilterCriteriaThreadLocal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.resource.jdbc.spi.StatementInspector;

/**
 * @author zhongyi@agree.com.cn
 */
@Slf4j
public class SqlInterceptor implements StatementInspector {

    private static final long serialVersionUID = -5811700911436215828L;

    @Override
    public String inspect(String sql) {
        log.info("sql inspect");
        return ResultFilterCriteriaThreadLocal.get().map(List::stream).map(stream -> stream.collect(Collectors.joining(" and "))).map(criteria -> this.processSql(sql, criteria)).map(item -> {
            ResultFilterCriteriaThreadLocal.remove();
            log.debug("sql modified: " + item);
            return item;
        }).orElse(sql);
    }

    private String processSql(String sql, String criteria) {
        return sql.replaceFirst("((from|FROM) (.*) (as )?(.*) (where|WHERE))", "$1 $5." + criteria + " and ");
    }
}
