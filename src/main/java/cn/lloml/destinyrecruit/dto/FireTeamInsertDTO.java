package cn.lloml.destinyrecruit.dto;

import cn.lloml.destinyrecruit.domain.GameMap;
import cn.lloml.destinyrecruit.domain.GameMode;
import cn.lloml.destinyrecruit.domain.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FireTeamInsertDTO extends BaseDTO {
    @NotEmpty
    @NotNull
    private String title;

    private String description;
    /**
     * 游戏地图id
     */
    @NotNull
    private Long gameMapId;

    private Date createdDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

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

    public Long getGameMapId() {
        return gameMapId;
    }

    public void setGameMapId(Long gameMapId) {
        this.gameMapId = gameMapId;
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