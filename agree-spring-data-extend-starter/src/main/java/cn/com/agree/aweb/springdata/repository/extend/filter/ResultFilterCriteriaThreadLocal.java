package cn.com.agree.aweb.springdata.repository.extend.filter;

import java.util.List;
import java.util.Optional;

/**
 * @author zhongyi@agree.com.cn
 */
public class ResultFilterCriteriaThreadLocal {

    private static final ThreadLocal<List<String>> FILTER = new ThreadLocal<List<String>>();

    public static void set(List<String> f) {
        FILTER.set(f);
    }

    public static Optional<List<String>> get() {
        return Optional.ofNullable(FILTER.get());
    }

    public static void remove() {
        FILTER.remove();
    }
}
