package cn.com.agree.aweb.dao.system;

import cn.com.agree.aweb.annotation.TemplateQuery;
import cn.com.agree.aweb.entity.form.query.SystemQueryBean;
import cn.com.agree.aweb.entity.po.system.SystemPO;
import cn.com.agree.aweb.springdata.repository.extend.AgreeBaseRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author YeSijun yesijun@agree.com.cn
 * @Version v1.0.0
 */
public interface SystemDao extends AgreeBaseRepository<SystemPO, String> {

	@TemplateQuery(key = "system-query-jpql")
	List<SystemPO> queryByTemplate(SystemQueryBean bean);

	@TemplateQuery(key = "system-query-jpql")
	Page<SystemPO> queryByTemplate(SystemQueryBean bean, Pageable page);
}
