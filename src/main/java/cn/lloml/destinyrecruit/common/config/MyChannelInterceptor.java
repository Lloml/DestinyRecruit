package cn.lloml.destinyrecruit.common.config;

import cn.lloml.destinyrecruit.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * WebSocket 通道拦截器（这里模拟两个测试 Token 方便测试，不做具体 Token 鉴权实现）
 *
 * @author mydlq
 */
@Slf4j
public class MyChannelInterceptor implements ChannelInterceptor {

    /**
     * 测试用户与 token 1
     */
    private User user1 = new User();

    /**
     * 从 Header 中获取 Token 进行验证，根据不同的 Token 区别用户
     *
     * @param message 消息对象
     * @param channel 通道对象
     * @return 验证后的用户信息
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        user1.setIdStr("123456");
        user1.setToken("123456");
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        String token = getToken(message);
        log.debug("websocket连接成功");
        log.debug(token);
        if (token != null && accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            Principal user = null;
            // 提前创建好两个测试 token 进行匹配，方便测试
            if (this.user1.getToken().equals(token)) {
                user = () -> this.user1.getIdStr();
            }
            accessor.setUser(user);
            return message;
        } else {
            return null;
        }
    }


    /**
     * 从 Header 中获取 TOKEN
     *
     * @param message 消息对象
     * @return TOKEN
     */
    private String getToken(Message<?> message) {
        Map<String, Object> headers = (Map<String, Object>) message.getHeaders().get("nativeHeaders");
        if (headers != null && headers.containsKey("token")) {
            List<String> token = (List<String>) headers.get("token");
            return String.valueOf(token.get(0));
        }
        return null;
    }

}
