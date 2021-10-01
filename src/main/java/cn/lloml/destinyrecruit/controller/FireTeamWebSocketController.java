package cn.lloml.destinyrecruit.controller;

import cn.lloml.destinyrecruit.dto.MemberChangeEventInfoDTO;
import cn.lloml.destinyrecruit.event.FireTeamMemberChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


@Controller()
public class FireTeamWebSocketController implements ApplicationListener<FireTeamMemberChangeEvent> {

    @Resource
    private SimpMessageSendingOperations simpMessageSendingOperations;

    public void sendFireTeamInfo(FireTeamMemberChangeEvent event) {
        MemberChangeEventInfoDTO memberChangeEventInfoDTO = event.getMemberChangeEventInfoDTO();
        var destination = "/FireTeam/" + event.getMemberChangeEventInfoDTO().getFireTeam().getId();
        System.out.println(destination);
        System.out.println(memberChangeEventInfoDTO);
        // 调用 STOMP 代理进行消息转发
        simpMessageSendingOperations.convertAndSend(destination, event.getMemberChangeEventInfoDTO());
    }


    @Override
    public void onApplicationEvent(FireTeamMemberChangeEvent event) {
        sendFireTeamInfo(event);
    }
}
