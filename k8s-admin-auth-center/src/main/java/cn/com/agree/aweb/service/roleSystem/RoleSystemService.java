package cn.com.agree.aweb.service.roleSystem;

import cn.com.agree.aweb.entity.form.query.RoleSystemQueryBean;
import cn.com.agree.aweb.entity.po.auth.center.RoleSystemPO;
import cn.com.agree.aweb.service.BaseService;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleSystemService extends BaseService<RoleSystemPO, String> {

	List<RoleSystemPO> queryByTemplateIds(RoleSystemQueryBean bean);

	void saveAll(RoleSystemQueryBean bean);

}
