package cn.com.agree.aweb.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

public class AuthTokenInterceptor implements RequestInterceptor {
    @Autowired
    private HttpServletRequest request;

    @Override
    public void apply(RequestTemplate template) {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (token == null && request != null) {
                token = request.getSession().getAttribute("webSocketToken").toString();
            }
            template.header(HttpHeaders.AUTHORIZATION, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
