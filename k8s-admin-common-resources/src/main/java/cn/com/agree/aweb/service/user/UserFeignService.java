package cn.com.agree.aweb.service.user;

import cn.com.agree.aweb.entity.po.auth.center.UserPO;
import cn.com.agree.aweb.entity.vo.RestResultMessage;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhongyi@agree.com.cn
 */
@FeignClient(value = "k8s-admin-auth-center")
@Service
public interface UserFeignService {

    /**
     * 获取用户详情
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("user/{id}")
    RestResultMessage<List<UserPO>> get(@PathVariable("id") String id);

    /**
     * 获取当前用户信息
     * 使用实体不好处理里面的Principal，直接使用json，处理时用jsonPath获取内容
     *
     * @return 当前用户
     */
    @GetMapping("user/current")
    String getCurrentUser();
}
