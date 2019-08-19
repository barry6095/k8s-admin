package cn.com.agree.aweb.jpa.listener;

import cn.com.agree.aweb.entity.po.BaseEntity;
import cn.com.agree.aweb.entity.po.auth.center.UserPO;
import cn.com.agree.aweb.entity.security.DefaultUserDetails;
import cn.com.agree.aweb.service.user.UserFeignService;
import cn.com.agree.aweb.util.SpringContextUtil;
import java.util.Map;
import java.util.Optional;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author zhongyi@agree.com.cn
 */
public class EntityListener {

//    private SecurityContext securityContext;
//    private RequestStorage requestStorage;
//    private UserFeignService userFeignService;

    @PrePersist
    public void prePersist(Object entity) {
        Optional.ofNullable(entity).filter(item -> item instanceof BaseEntity).map(BaseEntity.class::cast).ifPresent(item -> {
            String nowTime = System.currentTimeMillis() + "";
            item.setCreateTime(nowTime);
            item.setCreateUserId(getUserId());
            item.setCreateUserName(getUserName(getUserId()));
            item.setUpdateUserName(item.getCreateUserName());
            item.setUpdateUserId(getUserId());
            item.setUpdateTime(nowTime);
        });
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        Optional.ofNullable(entity).filter(item -> item instanceof BaseEntity).map(BaseEntity.class::cast).ifPresent(item -> {
            String nowTime = System.currentTimeMillis() + "";
            item.setUpdateUserId(getUserId());
            item.setUpdateUserName(getUserName(getUserId()));
            item.setUpdateTime(nowTime);
        });
    }

//    @PostLoad
//    public void postQuery(Object entity) {
//        Optional.ofNullable(entity).filter(item -> item instanceof BaseEntity).map(BaseEntity.class::cast).ifPresent(item -> {
//            item.setCreateUserName(Optional.ofNullable(item.getCreateUserId()).map(this::getUserName).orElse(""));
//            item.setUpdateUserName(Optional.ofNullable(item.getUpdateUserId()).map(this::getUserName).orElse(""));
//        });
//    }

    private String getUserId() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication).map(OAuth2Authentication::getUserAuthentication).map(Authentication::getDetails).map(Map.class::cast).map(item -> item.get("content")).map(Map.class::cast).map(item -> item.get("name").toString()).orElseGet(() -> Optional.ofNullable(authentication).map(OAuth2Authentication::getUserAuthentication).map(Authentication::getPrincipal).map(DefaultUserDetails.class::cast).map(DefaultUserDetails::getUsername).orElseThrow(() -> new IllegalStateException("用户未登录")));
    }
    private String getUserName(String userId){
        return SpringContextUtil.getBean(UserFeignService.class).get(userId).getContent().stream().findFirst().map(
            UserPO::getName).orElse("");
    }
}
