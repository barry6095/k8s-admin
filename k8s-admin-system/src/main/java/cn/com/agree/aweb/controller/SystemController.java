package cn.com.agree.aweb.controller;

import cn.com.agree.aweb.entity.form.query.PageRequest;
import cn.com.agree.aweb.entity.form.query.SystemQueryBean;
import cn.com.agree.aweb.entity.po.system.SystemPO;
import cn.com.agree.aweb.service.system.SystemService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YeSijun yesijun@agree.com.cn
 * @Version v1.0.0
 */
@RestController
@RequestMapping("system")
public class SystemController {

	@Autowired
	private SystemService systemService;

	@ApiOperation(code = 200,value = "/{id}", httpMethod = "GET", tags = "[feign]查询所有系统列表", responseContainer = "List", response = List.class)
	@GetMapping({ "/feign/queryAll" })
	public List<SystemPO> queryAll() throws TemplateException, IOException {
		return  systemService.findAll();
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "GET", tags = "查询分类", responseContainer = "List", response = System.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = true, allowMultiple = false, example = "system-test1", name = "分类ID", required = false)
	@GetMapping({ "/{id}", "" })
	public Object query(Optional<String> id, Optional<SystemQueryBean> bean, PageRequest page)
			throws TemplateException, IOException {
		if (id.isPresent()) {// return List<ScriptPO>
			return systemService.findById(id.get());
		} else if (bean.isPresent()) {
			return systemService.findByTempalteQuery(bean.get(), page.getPageRequest());
//			List<ScriptPO> scripts= return scripts;
		} else {
//			Page<ScriptPO> pageScript = scriptService.findAll(Example.of(script), page.getPageRequest());
			return systemService.findAll();
		}
	}

	@ApiOperation(code = 200, value = "", httpMethod = "Delete", tags = "删除分类", responseContainer = "List", response = System.class)
	@ApiParam(value = "{ids}", type = "List", allowEmptyValue = false, allowMultiple = true, example = "ids=id1&ids=id2", name = "分类ID集合", required = false)
	@DeleteMapping({ "" })
	public boolean delete(@RequestParam("ids") List<String> ids) {
		systemService.delete(ids);
		return true;
	}

	@ApiOperation(code = 200, value = "/{id}", httpMethod = "Delete", tags = "删除分类", responseContainer = "List", response = System.class)
	@ApiParam(value = "{id}", type = "String", allowEmptyValue = false, allowMultiple = false, example = "system-test1", name = "分类ID", required = false)
	@DeleteMapping({ "/{id}" })
	public boolean delete(@PathVariable(value = "id", required = false) Optional<String> id) {
		systemService.delete(id.orElseThrow(IllegalArgumentException::new));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "Put", tags = "修改分类", responseContainer = "List", response = System.class)
	@ApiParam(value = "{system}", type = "system.class", allowEmptyValue = false, allowMultiple = false, example = "system", name = "分类", required = false)
	@PutMapping({ "", "/" })
	public boolean put(Optional<SystemPO> system, Optional<String> roleIds) {
		systemService.update(system.orElseThrow(IllegalArgumentException::new), roleIds.orElse(""));
		return true;
	}

	@ApiOperation(code = 200, value = "/", httpMethod = "Post", tags = "增加分类", responseContainer = "List", response = System.class)
	@ApiParam(value = "{system}", type = "system.class", allowEmptyValue = false, allowMultiple = false, example = "new system()", name = "分类", required = false)
	@PostMapping({ "", "/" })
	public boolean post(Optional<SystemPO> system, Optional<String> roleIds) {
		systemService.add(system.orElseThrow(IllegalArgumentException::new), roleIds.orElse(""));
		return true;
	}

}
