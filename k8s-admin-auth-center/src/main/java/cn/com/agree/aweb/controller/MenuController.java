package cn.com.agree.aweb.controller;

import cn.com.agree.aweb.entity.form.query.PageRequest;
import cn.com.agree.aweb.entity.po.auth.center.MenuPO;
import cn.com.agree.aweb.entity.po.auth.center.UserPO;
import cn.com.agree.aweb.service.menu.MenuService;
import cn.com.agree.aweb.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public Principal getMenu(Principal principal) {
		return principal;
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "GET", tags = "查询菜单", responseContainer = "List", response = MenuPO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = true, allowMultiple = false, example = "menu-test1", name = "菜单ID", required = false)
	@GetMapping({ "", "/{id}" })
	public Object query(@PathVariable(value = "id", required = false) Optional<String> id, MenuPO menu,
			PageRequest page, String operationType, OAuth2Authentication auth) {
		// 懒加载,下面role没有内容
//		return Optional.ofNullable(auth.getUserAuthentication().getPrincipal()).map(DefaultUserDetails.class::cast)
//				.map(defaultUserDetails -> defaultUserDetails.getUser()).map(UserPO.class::cast).get().getRoles()
//				.stream().map(RolePO::getMenus).flatMap(List::stream).collect(Collectors.toList());
		// 下面是另一种实现
		return userService.findById(UserDetails.class.cast(auth.getUserAuthentication().getPrincipal()).getUsername())
				.map(UserPO::getRoles).map(list -> {
					List<List<MenuPO>> menus = new ArrayList<>();
					list.forEach(item -> menus.add(item.getMenus()));
					return menus.stream().flatMap(List::stream).distinct().collect(Collectors.toList());
				}).orElse(Collections.emptyList());
//		return  returnmenuService.findAll();
	}

	@ApiOperation(code = 200, value = "", httpMethod = "DELETE", tags = "删除菜单", responseContainer = "List", response = MenuPO.class)
	@ApiParam(value = "{ids}", type = "List", allowEmptyValue = false, allowMultiple = true, example = "ids=id1&ids=id2", name = "菜单ID集合", required = false)
	@DeleteMapping({ "" })
	public boolean delete(@RequestParam("ids") List<String> ids) {
		menuService.delete(ids);
		return true;
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "DELETE", tags = "删除菜单", responseContainer = "List", response = MenuPO.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = false, allowMultiple = false, example = "menu-test1", name = "菜单ID", required = false)
	@DeleteMapping({ "/{id}" })
	public boolean delete(@PathVariable(value = "id", required = false) Optional<String> id) {
		menuService.delete(id.orElseThrow(IllegalArgumentException::new));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "PUT", tags = "修改菜单", responseContainer = "List", response = MenuPO.class)
	@ApiParam(value = "{menu}", type = "MenuPO.class", allowEmptyValue = false, allowMultiple = false, example = "menu", name = "菜单", required = false)
	@PutMapping({ "", "/" })
	public boolean put(Optional<MenuPO> menu,String authorityIds) {
		menuService.update(menu.orElseThrow(IllegalArgumentException::new), authorityIds.split(","));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "POST", tags = "增加菜单", responseContainer = "List", response = MenuPO.class)
	@ApiParam(value = "{menu}", type = "MenuPO.class", allowEmptyValue = false, allowMultiple = false, example = "new MenuPO()", name = "菜单", required = false)
	@PostMapping({ "", "/" })
	public boolean post(Optional<MenuPO> menu,String authorityIds) {
		menuService.add(menu.orElseThrow(IllegalArgumentException::new), authorityIds.split(","));
		return true;
	}
}