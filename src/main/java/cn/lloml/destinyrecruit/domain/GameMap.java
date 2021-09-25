package cn.lloml.destinyrecruit.domain;

import java.io.Serializable;

public class GameMap extends BaseDomain {

    private String name;

    private Long gameModeId;

    private String imgUrl;

    private static final long serialVersionUID = 1L;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGameModeId() {
        return gameModeId;
    }

    public void setGameModeId(Long gameModeId) {
        this.gameModeId = gameModeId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}