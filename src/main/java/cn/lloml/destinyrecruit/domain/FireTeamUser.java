package cn.lloml.destinyrecruit.domain;

import java.io.Serializable;

public class FireTeamUser extends BaseDomain {

    private Long fireTeamId;

    private Long userId;

    /**
     * 0:非拥有者 1:拥有者
     */
    private boolean owner;

    private static final long serialVersionUID = 1L;


    public Long getFireTeamId() {
        return fireTeamId;
    }

    public void setFireTeamId(Long fireTeamId) {
        this.fireTeamId = fireTeamId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isOwner() {
        return owner;
    }
    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}