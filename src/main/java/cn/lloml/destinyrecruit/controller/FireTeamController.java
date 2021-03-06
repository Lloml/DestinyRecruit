package cn.lloml.destinyrecruit.controller;

import cn.lloml.destinyrecruit.common.CustomResponse;
import cn.lloml.destinyrecruit.common.ProjectResponseBody;

import cn.lloml.destinyrecruit.dto.MemberChangeEventInfoDTO;
import cn.lloml.destinyrecruit.dto.UserOfFireTeamDTO;
import cn.lloml.destinyrecruit.enumeration.MemberChangeType;
import cn.lloml.destinyrecruit.event.FireTeamMemberChangeEvent;
import cn.lloml.destinyrecruit.service.FireTeamService;

import cn.lloml.destinyrecruit.service.GameMapService;
import cn.lloml.destinyrecruit.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 火力战队控制类
 */
@RestController
@RequestMapping("/fire_team")
public class FireTeamController {
    @Resource
    private FireTeamService fireTeamService;
    @Resource
    private GameMapService gameMapService;
    @Resource
    private UserService userService;
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * @return 火力战队列表
     */
    @GetMapping
    public ResponseEntity<ProjectResponseBody> getFireTeamList() {
        return CustomResponse.ok("查询成功", fireTeamService.selectDTO());
    }

    /**
     * @param id 火力战队id
     * @return 火力战队
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseBody> getFireTeam(@PathVariable String id) {
        var fireTeam = fireTeamService.selectOneDtoPrimaryKey(Long.valueOf(id));
        if (fireTeam == null) {
            return CustomResponse.notFound("找不到这个火力战队");
        } else {
            return CustomResponse.ok("查询成功", fireTeam);
        }
    }

    @PostMapping("/{fireTeamId}/user/{userId}")
    public ResponseEntity<ProjectResponseBody> pushUserToFireTeam(@PathVariable String userId, @PathVariable String fireTeamId) {
        if (fireTeamService.selectOneDTOByUserId(Long.valueOf(userId)) != null) {
            return CustomResponse.badRequest("用户已经加入或者创建了一个火力战队");
        }
        var fireTeam = fireTeamService.selectOneDtoPrimaryKey(Long.valueOf(fireTeamId));
        if (fireTeam == null) {
            return CustomResponse.notFound("火力战队不存在");
        }
        if (fireTeam.getMemberList().size() >= fireTeam.getGameMode().getMaxNumber()) {
            return CustomResponse.badRequest("火力战队已经满员");
        }
        fireTeamService.pushUserToFireTeam(Long.valueOf(fireTeamId), Long.valueOf(userId), false);
        var fireTeamInfo = fireTeamService.selectOneDtoPrimaryKey(Long.valueOf(fireTeamId));
        //发送用户加入事件
        applicationEventPublisher.publishEvent(
                new FireTeamMemberChangeEvent(
                        this,
                        new MemberChangeEventInfoDTO(
                                fireTeamInfo,
                                userService.selectByPrimaryKey(Long.valueOf(userId)),
                                MemberChangeType.JOIN
                        )
                )
        );
        return CustomResponse.ok("加入火力战队成功");
    }

    @DeleteMapping("/{fireTeamId}/user/{userId}")
    public ResponseEntity<ProjectResponseBody> kickUserFromFireTeam(@PathVariable String userId, @PathVariable String fireTeamId) {
        var fireTeam = fireTeamService.selectOneDtoPrimaryKey(Long.valueOf(fireTeamId));
        if (fireTeam == null) {
            return CustomResponse.notFound("火力战队不存在！");
        }
        var memberList = fireTeam.getMemberList();
        UserOfFireTeamDTO user = null;
        for (UserOfFireTeamDTO member : memberList) {
            if (member.getId().equals(Long.valueOf(userId))) {
                user = member;
            }
        }
        if (user == null) {
            return CustomResponse.notFound("请求移除的用户不在这个火力战队中！");
        }
        //如果只有一个成员，直接删除火力战队
        if (fireTeam.getMemberList().size() == 1) {
            fireTeamService.deleteByPrimaryKey(Long.valueOf(fireTeamId));
            return CustomResponse.ok("移除成功，且火力战队已删除！");
        } else {
            fireTeamService.deleteUserFromFireTeam(Long.valueOf(fireTeamId), Long.valueOf(userId));
            //如果用户是火力战队队长，则他退出火力战队后设定新的队长
            if (user.isOwner()) {
                for (UserOfFireTeamDTO member : memberList) {
                    //第一个非队长成员，成为新队长
                    if (!member.isOwner()) {
                        fireTeamService.updateFireTeamMemberIsOwner(true, fireTeam.getId(), member.getId());
                        break;
                    }
                }
            }
            var fireTeamInfo = fireTeamService.selectOneDtoPrimaryKey(Long.valueOf(fireTeamId));
            //发送用户踢出事件
            applicationEventPublisher.publishEvent(
                    new FireTeamMemberChangeEvent(
                            this,
                            new MemberChangeEventInfoDTO(
                                    fireTeamInfo,
                                    userService.selectByPrimaryKey(Long.valueOf(userId)),
                                    MemberChangeType.KICK
                            )
                    )
            );
            return CustomResponse.ok("移除火力战队成功");
        }

    }

}
