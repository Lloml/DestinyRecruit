package cn.lloml.destinyrecruit.dto;

import cn.lloml.destinyrecruit.domain.User;

public class UserOfFireTeamDTO extends User {
    boolean owner;

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
