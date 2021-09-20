package cn.lloml.destinyrecruit.domain;

import java.io.Serializable;
import java.util.Date;

public class FireTeam implements Serializable {
    private Long id;

    private String title;

    private String description;

    /**
     * 游戏地图id
     */
    private Long gameMapId;

    private Date createdDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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