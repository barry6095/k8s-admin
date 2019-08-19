package cn.com.agree.aweb.controller;

import cn.com.agree.aweb.entity.form.query.PageRequest;
import cn.com.agree.aweb.entity.po.auth.center.GroupPO;
import cn.com.agree.aweb.service.group.GroupService;
import cn.com.agree.aweb.util.AuthUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/group")
public class GroupController {

	Logger logger = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public List<GroupPO> getGroups(Optional<String> userId) {
		return groupService.findByUserId(userId.orElse(AuthUtils.getCurrentUserId()));
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "GET", tags = "查询组织", responseContainer = "List", response = GroupPO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = true, allowMultiple = false, example = "group-test1", name = "组织ID", required = false)
	@GetMapping({ "", "/{id}" })
	public Object query(@PathVariable(value = "id", required = false) Optional<String> id, GroupPO group,
			PageRequest page) {
		return id.isPresent()
				? groupService.findById(id.get()).map(Collections::singletonList).orElse(Collections.emptyList())
				: groupService.findAll(Example.of(group), page.getPageRequest());
	}

	@ApiOperation(code = 200, value = "", httpMethod = "DELETE", tags = "删除组织", responseContainer = "List", response = GroupPO.class)
	@ApiParam(value = "{ids}", type = "List", allowEmptyValue = false, allowMultiple = true, example = "ids=id1&ids=id2", name = "组织ID集合", required = false)
	@DeleteMapping({ "" })
	public boolean delete(@RequestParam("ids") List<String> ids) {
		groupService.delete(ids);
		return true;
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "DELETE", tags = "删除组织", responseContainer = "List", response = GroupPO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = false, allowMultiple = false, example = "group-test1", name = "组织ID", required = false)
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable("id") Optional<String> id) {
		groupService.delete(id.orElseThrow(IllegalArgumentException::new));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "PUT", tags = "修改组织", responseContainer = "List", response = GroupPO.class)
	@ApiParam(value = "{group}", type = "GroupPO.class", allowEmptyValue = false, allowMultiple = false, example = "group", name = "组织", required = false)
	@PutMapping({ "", "/" })
	public boolean put(Optional<GroupPO> group, Optional<String> userIds) {
		groupService.update(group.orElseThrow(IllegalArgumentException::new), userIds.orElse("").split(","));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "POST", tags = "增加组织", responseContainer = "List", response = GroupPO.class)
	@ApiParam(value = "{group}", type = "GroupPO.class", allowEmptyValue = false, allowMultiple = false, example = "new GroupPO()", name = "组织", required = false)
	@PostMapping({ "", "/" })
	public boolean post(Optional<GroupPO> group) {
		groupService.add(group.orElseThrow(IllegalArgumentException::new));
		return true;
	}
}