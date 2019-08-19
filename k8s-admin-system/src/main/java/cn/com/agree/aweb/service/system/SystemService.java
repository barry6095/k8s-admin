package cn.com.agree.aweb.service.system;

import cn.com.agree.aweb.entity.form.query.SystemQueryBean;
import cn.com.agree.aweb.entity.po.system.SystemPO;
import cn.com.agree.aweb.service.BaseService;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author YeSijun yesijun@agree.com.cn
 * @Version v1.0.0
 */
@Transactional
public interface SystemService extends BaseService<SystemPO, String> {
	List<SystemPO> findByTempalteQuery(SystemQueryBean Bean) throws TemplateException, IOException;
    Page<SystemPO> findByTempalteQuery(SystemQueryBean Bean, Pageable page) throws TemplateException, IOException;

    public void add(SystemPO t, String roleIds);
    public void update(SystemPO t, String roleIds);
}
