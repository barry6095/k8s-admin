package cn.com.agree.aweb.util;

import cn.com.agree.aweb.entity.security.DefaultUserDetails;
import java.util.Map;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @description:
 * @author: YeSijun yesijun@agree.com.cn
 * @version: 1.0
 * @create: 2019-06-05 16:14
 **/
public class AuthUtils {
    public static String getCurrentUserId() {
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(authentication).map(OAuth2Authentication::getUserAuthentication).map(Authentication::getDetails).map(Map.class::cast).map(item -> item.get("content")).map(Map.class::cast).map(item -> item.get("name").toString()).orElseGet(() -> Optional.ofNullable(authentication).map(OAuth2Authentication::getUserAuthentication).map(Authentication::getPrincipal).map(DefaultUserDetails.class::cast).map(
            DefaultUserDetails::getUsername).orElseThrow(() -> new IllegalStateException("用户未登录")));
    }
}