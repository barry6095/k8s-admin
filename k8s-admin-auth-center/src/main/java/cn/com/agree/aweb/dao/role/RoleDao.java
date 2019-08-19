package cn.com.agree.aweb.dao.role;

import cn.com.agree.aweb.annotation.TemplateQuery;
import cn.com.agree.aweb.entity.form.query.RoleQueryBean;
import cn.com.agree.aweb.entity.po.auth.center.RolePO;
import cn.com.agree.aweb.springdata.repository.extend.AgreeBaseRepository;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author YeSijun yesijun@agree.com.cn
 * @Version v2.0.0
 * @Date 2019-05-10 10：00:00
 */

public interface RoleDao extends AgreeBaseRepository<RolePO, String> {
	List<RolePO> findByName(String name);

	@Query("select r from RolePO r left join fetch r.menus where r.name=?1")
	List<RolePO> findByNameWithMenus(String name);

	<S extends RolePO> Page<S> findAll(Example<S> example, Pageable page);

	@TemplateQuery(key = "role-query-jpql")
	Page<RolePO> queryByTemplate(RoleQueryBean bean, Pageable page);

	@TemplateQuery(key = "role-query-jpql-by-ids")
	List<RolePO> findByTemplateIds(RoleQueryBean bean);
}