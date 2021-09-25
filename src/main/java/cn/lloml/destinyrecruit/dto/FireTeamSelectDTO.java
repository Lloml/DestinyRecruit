package cn.lloml.destinyrecruit.dto;

import cn.lloml.destinyrecruit.domain.GameMap;
import cn.lloml.destinyrecruit.domain.GameMode;
import cn.lloml.destinyrecruit.domain.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FireTeamSelectDTO extends BaseDTO{

    private String title;

    private String description;

    /**
     * 游戏模式
     */
    private GameMode gameMode;

    /**
     * 游戏地图id
     */
    private GameMap gameMap;

    /**
     * 拥有者
     */
    private User Owner;

    /**
     * 成员列表
     */
    private List<UserOfFireTeamDTO> memberList;

    private Date createdDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User owner) {
        Owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<UserOfFireTeamDTO> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<UserOfFireTeamDTO> memberList) {
        this.memberList = memberList;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}