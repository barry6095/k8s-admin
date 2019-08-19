package cn.com.agree.aweb.service.roleSystem;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author YeSijun yesijun@agree.com.cn
 *
 * @date 2019-05-09 17:34:25
 */
@FeignClient(value = "k8s-admin-auth-center")
public interface RoleSystemFeignService {

    /**
     * 新增时添加关联
     *
     */
    @RequestMapping(value="/role-system/",method= RequestMethod.PUT)
    JSONObject add(@RequestParam(name = "roleId") String roleId,
        @RequestParam(name = "systemId") String systemId,
        @RequestParam(name = "roleIds") String roleIds,
        @RequestParam(name = "systemIds") String systemIds);

}
