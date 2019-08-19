package cn.com.agree.aweb.controller;

import cn.com.agree.aweb.entity.form.query.PageRequest;
import cn.com.agree.aweb.entity.form.query.RoleQueryBean;
import cn.com.agree.aweb.entity.po.auth.center.RolePO;
import cn.com.agree.aweb.service.role.RoleService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

	Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getRole(Principal principal) {
		return principal;
	}

	@ApiOperation(code = 200,value = "/{id}", httpMethod = "GET", tags = "[feign]根据ids查询角色列表", responseContainer = "List", response = List.class)
	@GetMapping({ "/feign/queryByIds" })
	public List<RolePO> queryByIds(Optional<RoleQueryBean> bean) throws TemplateException, IOException {
		return  roleService.findByIds(bean.get());
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "GET", tags = "查询角色", responseContainer = "List", response = Page.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = true, allowMultiple = false, example = "role-test1", name = "角色ID", required = false)
	@GetMapping({ "", "/{id}" })
	public Object query(Optional<String> id, Optional<RoleQueryBean> bean, PageRequest page)
			throws TemplateException, IOException {
		if (id.isPresent()) {// return List<ScriptPO>
			return roleService.findById(id.get());
		} else  {
			return roleService.findByTempalteQuery(bean.get(), page.getPageRequest());
		}
	}

	@ApiOperation(code = 200, value = "", httpMethod = "DELETE", tags = "删除角色", responseContainer = "List", response = RolePO.class)
	@ApiParam(value = "{ids}", type = "List", allowEmptyValue = false, allowMultiple = true, example = "ids=id1&ids=id2", name = "角色ID集合", required = false)
	@DeleteMapping({ "" })
	public boolean delete(@RequestParam("ids") List<String> ids) {
		roleService.delete(ids);
		return true;
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "DELETE", tags = "删除角色", responseContainer = "List", response = RolePO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = false, allowMultiple = false, example = "role-test1", name = "角色ID", required = false)
	@DeleteMapping({ "/{id}" })
	public boolean delete(@PathVariable(value = "id", required = false) Optional<String> id) {
		roleService.delete(id.orElseThrow(IllegalArgumentException::new));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "PUT", tags = "修改角色", responseContainer = "List", response = RolePO.class)
	@ApiParam(value = "{role}", type = "RolePO.class", allowEmptyValue = false, allowMultiple = false, example = "role", name = "角色", required = false)
	@PutMapping({ "", "/" })
	public boolean put(Optional<RolePO> role,String menuIds) {
		roleService.update(role.orElseThrow(IllegalArgumentException::new), menuIds.split(","));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "Post", tags = "增加角色", responseContainer = "List", response = RolePO.class)
	@ApiParam(value = "{role}", type = "RolePO.class", allowEmptyValue = false, allowMultiple = false, example = "new RolePO()", name = "角色", required = false)
	@PostMapping({ "", "/" })
	public boolean post(Optional<RolePO> role,Optional<String> menuIds) {
		roleService.add(role.orElseThrow(IllegalArgumentException::new), menuIds.orElse("").split(","));
		return true;
	}
}