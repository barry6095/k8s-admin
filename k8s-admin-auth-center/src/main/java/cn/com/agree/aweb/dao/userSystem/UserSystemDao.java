package cn.com.agree.aweb.dao.userSystem;

import cn.com.agree.aweb.annotation.TemplateQuery;
import cn.com.agree.aweb.entity.form.query.RoleSystemQueryBean;
import cn.com.agree.aweb.entity.po.auth.center.RoleSystemPO;
import cn.com.agree.aweb.springdata.repository.extend.AgreeBaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSystemDao extends AgreeBaseRepository<RoleSystemPO, String> {
	
	@TemplateQuery(key = "role-system-query-jpql-by-roleId-systemId")
	List<RoleSystemPO> findByTemplateIds(RoleSystemQueryBean bean);
}