package cn.com.agree.aweb.dao.menu;

import cn.com.agree.aweb.entity.po.auth.center.MenuPO;
import cn.com.agree.aweb.springdata.repository.extend.AgreeBaseRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends AgreeBaseRepository<MenuPO, String> {
	List<MenuPO> findByName(String name);

	@Query("select m from MenuPO m left join fetch m.authorities where m.name=?1")
	List<MenuPO> findByNameWithAuthority(String name);
}