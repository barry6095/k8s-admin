package cn.com.agree.aweb.springdata.repository.extend.filter;

/**
 * @author zhongyi@agree.com.cn
 */
public interface ResultFilter {
    /**
     * 获取result过滤的条件
     *
     * @param input 输入值
     * @return 输出条件
     */
    String getCriteria(String input);
}
