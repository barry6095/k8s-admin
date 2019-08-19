package cn.com.agree.aweb.service.menu;

import cn.com.agree.aweb.entity.po.auth.center.MenuPO;
import cn.com.agree.aweb.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MenuService extends BaseService<MenuPO, String> {
	public void add(MenuPO t, String[] authorityIds);
	public void update(MenuPO t, String[] authorityIds);
}
