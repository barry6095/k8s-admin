package cn.com.agree.aweb.service.system;

import cn.com.agree.aweb.dao.system.SystemDao;
import cn.com.agree.aweb.entity.enums.constant.RoleContant;
import cn.com.agree.aweb.entity.form.query.SystemQueryBean;
import cn.com.agree.aweb.entity.po.system.SystemPO;
import cn.com.agree.aweb.service.roleSystem.RoleSystemFeignService;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author YeSijun yesijun@agree.com.cn
 * @Version v1.0.0
 */
@Service
public class SystemServiceImpl implements SystemService {

	@Autowired
	private SystemDao scriptDao;
	@Autowired
	private RoleSystemFeignService roleSystemFeignService;

	@Override
	public SystemPO add(SystemPO t) {
		return scriptDao.save(t);
	}

	@Override
	public void delete(SystemPO t) {
		scriptDao.delete(t);
	}

	@Override
	public void delete(String id) {
		this.findById(id).ifPresent(this::delete);
	}

	@Override
	public SystemPO update(SystemPO t) {
		return scriptDao.saveAndFlush(t);
	}

	@Override
	public List<SystemPO> findAll() {
		return scriptDao.findAll();
	}

	@Override
	public Optional<SystemPO> findById(String id) {
		return scriptDao.findById(id);
	}

	@Override
	public Page<SystemPO> findAll(Example<SystemPO> example, Pageable page) {
		return scriptDao.findAll(example, page);
	}

	@Override
	public void delete(Iterable<String> ids) {
		scriptDao.deleteAll(scriptDao.findAllById(ids));
	}

	@Override
	public List<SystemPO> findByTempalteQuery(SystemQueryBean bean) throws TemplateException, IOException {
		return scriptDao.queryByTemplate(bean);
	}

	@Override
	public Page<SystemPO> findByTempalteQuery(SystemQueryBean bean, Pageable page)
			throws TemplateException, IOException {
		return scriptDao.queryByTemplate(bean, page);
	}

	@Override
	public void add(SystemPO t, String  roleIds) {
		scriptDao.save(t);
		JSONObject jsonObject= roleSystemFeignService.add(RoleContant.ADMIN_ID.getName(),t.getId(),roleIds,null);
	}

	@Override
	public void update(SystemPO t, String roleIds) {
		scriptDao.saveAndFlush(t);
	}

}
