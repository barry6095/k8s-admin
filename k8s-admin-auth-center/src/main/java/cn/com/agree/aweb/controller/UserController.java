package cn.com.agree.aweb.controller;

import cn.com.agree.aweb.entity.form.query.PageRequest;
import cn.com.agree.aweb.entity.po.auth.center.UserPO;
import cn.com.agree.aweb.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
@RequestMapping("/user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getUser(Principal principal) {
		return principal;
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "GET", tags = "查询用户", responseContainer = "List", response = UserPO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = true, allowMultiple = false, example = "user-test1", name = "用户ID", required = false)
	@GetMapping({ "", "/{id}" })
	public Object query(@PathVariable(value = "id", required = false) Optional<String> id, UserPO user,
			PageRequest page) {
		return id.isPresent()
				? userService.findById(id.get()).map(Collections::singletonList).orElse(Collections.emptyList())
				: userService.findAll(Example.of(user), page.getPageRequest());
	}

	@ApiOperation(code = 200, value = "", httpMethod = "DELETE", tags = "删除用户", responseContainer = "List", response = UserPO.class)
	@ApiParam(value = "{ids}", type = "List", allowEmptyValue = false, allowMultiple = true, example = "ids=id1&ids=id2", name = "用户ID集合", required = false)
	@DeleteMapping({ "" })
	public boolean delete(@RequestParam("ids") List<String> ids) {
		userService.delete(ids);
		return true;
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "DELETE", tags = "删除用户", responseContainer = "List", response = UserPO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = false, allowMultiple = false, example = "user-test1", name = "用户ID", required = false)
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable("id") Optional<String> id) {
		userService.delete(id.orElseThrow(IllegalArgumentException::new));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "PUT", tags = "修改用户", responseContainer = "List", response = UserPO.class)
	@ApiParam(value = "{user}", type = "UserPO.class", allowEmptyValue = false, allowMultiple = false, example = "user", name = "用户", required = false)
	@PutMapping({ "", "/" })
	public boolean put(Optional<UserPO> user,Optional<String> roleIds, Optional<String> groupIds) {
		userService.update(user.orElseThrow(IllegalArgumentException::new), roleIds.orElse("").split(","),groupIds.orElse("").split(","));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "POST", tags = "增加用户", responseContainer = "List", response = UserPO.class)
	@ApiParam(value = "{user}", type = "UserPO.class", allowEmptyValue = false, allowMultiple = false, example = "new UserPO()", name = "用户", required = false)
	@PostMapping({ "", "/" })
	public boolean post(Optional<UserPO> user, Optional<String> roleIds, Optional<String> groupIds) {
		userService.add(user.orElseThrow(IllegalArgumentException::new), roleIds.orElse("").split(","), groupIds.orElse("").split(","));
		return true;
	}
}