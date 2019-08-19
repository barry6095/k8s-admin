package cn.com.agree.aweb.service.menu;

import cn.com.agree.aweb.dao.authority.AuthorityDao;
import cn.com.agree.aweb.dao.menu.MenuDao;
import cn.com.agree.aweb.dao.role.RoleDao;
import cn.com.agree.aweb.entity.enums.constant.RoleContant;
import cn.com.agree.aweb.entity.po.auth.center.MenuPO;
import cn.com.agree.aweb.service.BaseServiceImpl;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuPO, String> implements MenuService {

	@Autowired
	private MenuDao menuDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public JpaRepository<MenuPO, String> getDao() {
		return menuDao;
	}

	@Override
	public void add(MenuPO t, String[] authorityIds) {
		t.setAuthorities(Arrays.asList(authorityIds).stream().map(authorityDao::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
		menuDao.save(t);
		roleDao.findById(RoleContant.ADMIN_ID.getName()).ifPresent(rol_admin -> {rol_admin.getMenus().add(t);roleDao.save(rol_admin);});
	}

	@Override
	public void update(MenuPO t, String[] authorityIds) {
		t.setAuthorities(Arrays.asList(authorityIds).stream().map(authorityDao::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
		menuDao.saveAndFlush(t);
	}

}
