package cn.com.agree.aweb.springdata.repository.extend;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author zhongyi@agree.com.cn
 */
@NoRepositoryBean
public interface AgreeBaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
//	<X> List<X> queryByTemplate(String key) throws TemplateException, IOException;
//	<X> List<X> queryByTemplate(String key, Object params) throws TemplateException, IOException;
//	<X> List<X> queryByTemplate(String key, Object params, boolean isNativeQuery, boolean isPreparedQuery) throws TemplateException, IOException;
}
