package cn.lloml.destinyrecruit.controller;

import cn.lloml.destinyrecruit.dto.MessageBody;
import cn.lloml.destinyrecruit.dto.WebSocketMessageBodyDTO;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.security.Principal;


@Controller()
public class FireTeamWebSocketController {

    @Resource
    private SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * 点对点发送消息，将消息发送到指定用户
     */
    @MessageMapping("/test")
    public void sendTopicMessage(MessageBody messageBody) {
        System.out.println(messageBody);
        // 将消息发送到 WebSocket 配置类中配置的代理中（/topic）进行消息转发
        simpMessageSendingOperations.convertAndSend(messageBody.getDestination(), "获取成功");
    }

}
