package cn.com.agree.aweb.service.role;

import cn.com.agree.aweb.dao.menu.MenuDao;
import cn.com.agree.aweb.dao.role.RoleDao;
import cn.com.agree.aweb.entity.enums.constant.RoleContant;
import cn.com.agree.aweb.entity.form.query.RoleQueryBean;
import cn.com.agree.aweb.entity.po.auth.center.RolePO;
import cn.com.agree.aweb.service.BaseServiceImpl;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RolePO, String> implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;
	@Override
	public RolePO add(RolePO t) {
		return roleDao.save(t);
	}

	@Override
	public JpaRepository<RolePO, String> getDao() {
		return roleDao;
	}

	@Override
	public void add(RolePO t, String[] menuIds) {
		t.setMenus(RoleContant.ADMIN_ID.getName().equals(t.getId())?menuDao.findAll():menuDao.findAllById(Arrays.asList(menuIds)));
		roleDao.save(t);
	}

	public RolePO update(RolePO t) {
		return roleDao.saveAndFlush(t);
	}

	public void delete(RolePO t) {
		roleDao.delete(t);
	}

	public void delete(String id) {
		this.findById(id).ifPresent(this::delete);
	}

	@Override
	public void update(RolePO t, String[] menuIds) {
		t.setMenus(RoleContant.ADMIN_ID.getName().equals(t.getId()) ? menuDao.findAll():menuDao.findAllById(Arrays.asList(menuIds)));
		roleDao.saveAndFlush(t);
	}

	public List<RolePO> findAll() {
		return roleDao.findAll();
	}

	public Optional<RolePO> findById(String id) {
		return roleDao.findById(id);
	}

	public Page<RolePO> findAll(Example<RolePO> example, Pageable page) {
		return roleDao.findAll(example, page);
	}

	public void delete(Iterable<String> ids) {
		roleDao.deleteAll(roleDao.findAllById(ids));
	}

	@Override
	public Page<RolePO> findByTempalteQuery(RoleQueryBean bean, Pageable page) throws TemplateException, IOException {
		return roleDao.queryByTemplate(bean, page);
	}

	@Override
	public List<RolePO> findByIds(RoleQueryBean bean) throws TemplateException, IOException {
		return roleDao.findByTemplateIds(bean);
	}

}
