package cn.com.agree.aweb.service.roleSystem;

import cn.com.agree.aweb.dao.userSystem.UserSystemDao;
import cn.com.agree.aweb.entity.enums.constant.RoleContant;
import cn.com.agree.aweb.entity.form.query.RoleSystemQueryBean;
import cn.com.agree.aweb.entity.po.auth.center.RoleSystemPO;
import cn.com.agree.aweb.entity.po.system.SystemPO;
import cn.com.agree.aweb.entity.vo.RestResultMessage;
import cn.com.agree.aweb.service.BaseServiceImpl;
import cn.com.agree.aweb.service.system.SystemFeignService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleSystemServiceImpl extends BaseServiceImpl<RoleSystemPO, String> implements RoleSystemService {

	@Autowired
	private UserSystemDao userSystemDao;

	@Autowired
	private SystemFeignService systemFeignService;

	@Override
	public JpaRepository<RoleSystemPO, String> getDao() {
		return userSystemDao;
	}

	@Override
	public List<RoleSystemPO> queryByTemplateIds(RoleSystemQueryBean bean) {
//		return beans.stream().map(bean -> userSystemDao.findByTemplateIds(bean)).flatMap(List::stream)
//				.collect(Collectors.toList());
		return userSystemDao.findByTemplateIds(bean);
//		.map(MenuPO::getAuthorities).flatMap(List::stream).collect(Collectors.toList()));
	}

	@Override
	public void saveAll(RoleSystemQueryBean bean) {
		// 先删除原来所有数据，然后重新添加，达到修改或新增效果
		List<RoleSystemPO> roleSystems = new ArrayList<RoleSystemPO>();
		RoleSystemPO rs = null;
		String roleId = bean.getRoleId();
		String systemId = bean.getSystemId();
		List<String> systemIds = bean.getSystemIds();
		List<String> roleIds = bean.getRoleIds();

		if(systemId != null && systemId.length()>0&&roleId != null && roleId.length()>0) {
			//存在roleId和systemId,直接关联
			rs = new RoleSystemPO();
			rs.setRoleId(roleId);
			rs.setSystemId(systemId);
			userSystemDao.save(rs);
//			return;
		}

		if (roleId != null && roleId.length()>0) {
			if (RoleContant.ADMIN_ID.getName().equals(roleId)) {
				//roleid为系统管理员的时候触发，但是前端屏蔽了这个操作，正常情况下不会出现
				//关联全部
				RestResultMessage<List<SystemPO>> restResultMessage=systemFeignService.queryAll();
				List<SystemPO>  systems= restResultMessage.getContent();
				systems.stream().map(system -> {
					RoleSystemPO rsp = new RoleSystemPO();
					rsp.setRoleId(roleId);
					rsp.setSystemId(system.getId());
					return rsp;
				}).forEach(userSystemDao::save);
			}
			if(systemIds!= null){
				for (int index = 0, size = systemIds.size(); index < size; index++) {
					rs = new RoleSystemPO();
					rs.setRoleId(roleId);
					rs.setSystemId(systemIds.get(index));
					roleSystems.add(rs);
				}
				// 根据roleId删除原有数据
				bean.setRoleIds(null);
				bean.setSystemId(null);
				bean.setSystemIds(null);
				userSystemDao.deleteAll(userSystemDao.findByTemplateIds(bean));
				// 新增关联
				userSystemDao.saveAll(roleSystems);
			}
		} else if (systemId != null && systemId.length()>0) {
			if (roleIds != null) {
				for (int index = 0, size = roleIds.size(); index < size; index++) {
					rs = new RoleSystemPO();
					rs.setSystemId(systemId);
					rs.setRoleId(roleIds.get(index));
					roleSystems.add(rs);
				}

				//直接添加管理员角色
				rs = new RoleSystemPO();
				rs.setSystemId(systemId);
				rs.setRoleId(RoleContant.ADMIN_ID.getName());
				roleSystems.add(rs);

				// 根据systemId删除原有数据
				bean.setRoleIds(null);
				bean.setRoleId(null);
				bean.setSystemIds(null);
				userSystemDao.deleteAll(userSystemDao.findByTemplateIds(bean));
				// 新增
				userSystemDao.saveAll(roleSystems);
			}
		}
	}
}
