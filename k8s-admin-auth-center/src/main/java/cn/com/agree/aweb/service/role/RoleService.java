package cn.com.agree.aweb.service.role;

import cn.com.agree.aweb.entity.form.query.RoleQueryBean;
import cn.com.agree.aweb.entity.po.auth.center.RolePO;
import cn.com.agree.aweb.service.BaseService;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleService extends BaseService<RolePO, String> {
	public void add(RolePO t, String[] menuIds);

	public void update(RolePO t, String[] menuIds);

	Page<RolePO> findByTempalteQuery(RoleQueryBean Bean, Pageable page) throws TemplateException, IOException;

	List<RolePO> findByIds(RoleQueryBean Bean) throws TemplateException, IOException;

}
