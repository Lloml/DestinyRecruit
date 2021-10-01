package cn.lloml.destinyrecruit.dto;

import cn.lloml.destinyrecruit.domain.User;
import cn.lloml.destinyrecruit.enumeration.MemberChangeType;

public class MemberChangeEventInfoDTO {

    public MemberChangeEventInfoDTO(FireTeamSelectDTO fireTeamSelectDTO, User user,MemberChangeType memberChangeType) {
        this.fireTeam = fireTeamSelectDTO;
        this.user  = user;
        this.memberChangeType = memberChangeType;
    }

    private FireTeamSelectDTO fireTeam;

    private MemberChangeType memberChangeType;

    private User user;

    public FireTeamSelectDTO getFireTeam() {
        return fireTeam;
    }

    public void setFireTeam(FireTeamSelectDTO fireTeam) {
        this.fireTeam = fireTeam;
    }

    public MemberChangeType getMemberChangeType() {
        return memberChangeType;
    }

    public void setMemberChangeType(MemberChangeType memberChangeType) {
        this.memberChangeType = memberChangeType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
