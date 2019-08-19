package cn.com.agree.aweb.controller;

import cn.com.agree.aweb.entity.form.query.PageRequest;
import cn.com.agree.aweb.entity.po.auth.center.AuthorityPO;
import cn.com.agree.aweb.service.authority.AuthorityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authority")
public class AuthorityController {

	Logger logger = LoggerFactory.getLogger(AuthorityController.class);

	@Autowired
	private AuthorityService authorityService;

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "GET", tags = "查询权限", responseContainer = "List", response = AuthorityPO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = true, allowMultiple = false, example = "authority-test1", name = "权限ID", required = false)
	@GetMapping({ "", "/{id}" })
	public Object query(@PathVariable(value = "id", required = false) String id, AuthorityPO authority,
			PageRequest page) {
		if (id == null) {
			Page<AuthorityPO> pageAuthority = authorityService.findAll(Example.of(authority), page.getPageRequest());
			return pageAuthority;
		} else {
			return authorityService.findById(id).map(Collections::singletonList).orElse(Collections.emptyList());
		}
	}

	@ApiOperation(code = 200, value = "", httpMethod = "DELETE", tags = "删除权限", responseContainer = "List", response = AuthorityPO.class)
	@ApiParam(value = "{ids}", type = "List", allowEmptyValue = false, allowMultiple = true, example = "ids=id1&ids=id2", name = "权限ID集合", required = false)
	@DeleteMapping({ "" })
	public boolean delete(@RequestParam("ids") List<String> ids) {
		authorityService.delete(ids);
		return true;
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "DELETE", tags = "删除权限", responseContainer = "List", response = AuthorityPO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = false, allowMultiple = false, example = "authority-test1", name = "权限ID", required = false)
	@DeleteMapping({ "/{id}" })
	public boolean delete(@PathVariable(value = "id", required = false) Optional<String> id) {
		authorityService.delete(id.orElseThrow(IllegalArgumentException::new));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "PUT", tags = "修改权限", responseContainer = "List", response = AuthorityPO.class)
	@ApiParam(value = "{authority}", type = "AuthorityPO.class", allowEmptyValue = false, allowMultiple = false, example = "authority", name = "权限", required = false)
	@PutMapping({ "", "/" })
	public boolean put(Optional<AuthorityPO> authority, OAuth2Authentication auth) {
		authorityService.add(authority.orElseThrow(IllegalArgumentException::new));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "Post", tags = "增加权限", responseContainer = "List", response = AuthorityPO.class)
	@ApiParam(value = "{authority}", type = "AuthorityPO.class", allowEmptyValue = false, allowMultiple = false, example = "new AuthorityPO()", name = "权限", required = false)
	@PostMapping({ "", "/" })
	public boolean post(Optional<AuthorityPO> authority) {
		authorityService.add(authority.orElseThrow(IllegalArgumentException::new));
		return true;
	}
}