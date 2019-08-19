package cn.com.agree.aweb.controller;

import cn.com.agree.aweb.entity.form.query.RoleSystemQueryBean;
import cn.com.agree.aweb.entity.po.auth.center.GroupPO;
import cn.com.agree.aweb.service.roleSystem.RoleSystemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role-system")
public class RoleSystemControler {
    @Autowired
    private RoleSystemService roleSystemService;
    
	@ApiOperation(code = 200, value = "/{id}", httpMethod = "GET", tags = "查询角色-系统关联", responseContainer = "List", response = GroupPO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = true, allowMultiple = false, example = "roleSystem-test1", name = "角色-系统关联ID", required = false)
	@GetMapping({ ""})
	public Object query(Optional<RoleSystemQueryBean> bean) {
		return  roleSystemService.queryByTemplateIds(bean.orElseThrow(IllegalArgumentException::new));
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "PUT", tags = "修改角色-系统关联", responseContainer = "List", response = GroupPO.class)
	@ApiParam(value = "{roleSystem}", type = "GroupPO.class", allowEmptyValue = false, allowMultiple = false, example = "roleSystem", name = "角色-系统关联", required = false)
	@PutMapping({ "" })
	public boolean put(Optional<RoleSystemQueryBean> bean) {
		roleSystemService.saveAll(bean.orElseThrow(IllegalArgumentException::new));
		return true;
	}

}
