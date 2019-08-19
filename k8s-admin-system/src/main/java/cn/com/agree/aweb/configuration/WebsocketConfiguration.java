package cn.com.agree.aweb.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {
	   @Override
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	        /**
	         * 注册 Stomp的端点
	         * addEndpoint：添加STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址
	         * withSockJS：指定端点使用SockJS协议
	          */
	        registry.addEndpoint("/websocket")
	                .setAllowedOrigins("*") // 添加允许跨域访问
	                .withSockJS();
	    }

	    @Override
	    public void configureMessageBroker(MessageBrokerRegistry registry) {
	        /**
	         * 配置消息代理
	         * 启动简单Broker，消息的发送的地址符合配置的前缀来的消息才发送到这个broker
	         */
	        registry.enableSimpleBroker("/topic");
	    }
}