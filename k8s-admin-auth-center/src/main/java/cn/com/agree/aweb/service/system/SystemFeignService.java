package cn.com.agree.aweb.service.system;

import cn.com.agree.aweb.entity.po.system.SystemPO;
import cn.com.agree.aweb.entity.vo.RestResultMessage;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author YeSijun yesijun@agree.com.cn
 *
 * @date 2019-05-15 21:09:25
 */
@FeignClient(value = "k8s-admin-system")
public interface SystemFeignService {

    /**
     * 获取系统列表
     *
     * @return 系统列表
     */
    @RequestMapping(value="/system/feign/queryAll",method= RequestMethod.GET)
    RestResultMessage<List<SystemPO>> queryAll();

}
