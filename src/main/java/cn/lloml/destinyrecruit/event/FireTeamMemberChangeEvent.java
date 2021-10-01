package cn.lloml.destinyrecruit.event;

import cn.lloml.destinyrecruit.domain.User;
import cn.lloml.destinyrecruit.dto.FireTeamSelectDTO;
import cn.lloml.destinyrecruit.dto.MemberChangeEventInfoDTO;
import cn.lloml.destinyrecruit.dto.UserDTO;
import cn.lloml.destinyrecruit.dto.UserOfFireTeamDTO;
import cn.lloml.destinyrecruit.enumeration.MemberChangeType;
import org.springframework.context.ApplicationEvent;

import java.util.List;

public class FireTeamMemberChangeEvent extends ApplicationEvent {


    private MemberChangeEventInfoDTO memberChangeEventInfoDTO;

    public FireTeamMemberChangeEvent(Object source) {
        super(source);
    }

    public FireTeamMemberChangeEvent(Object source, MemberChangeEventInfoDTO memberChangeEventInfoDTO) {
        super(source);
        this.memberChangeEventInfoDTO = memberChangeEventInfoDTO;
    }

    public MemberChangeEventInfoDTO getMemberChangeEventInfoDTO() {
        return memberChangeEventInfoDTO;
    }

    public void setMemberChangeEventInfoDTO(MemberChangeEventInfoDTO memberChangeEventInfoDTO) {
        this.memberChangeEventInfoDTO = memberChangeEventInfoDTO;
    }

}

