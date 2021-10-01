package cn.lloml.destinyrecruit.dto;

import cn.lloml.destinyrecruit.domain.GameMap;
import cn.lloml.destinyrecruit.domain.GameMode;

import java.util.List;

public class GameModeDTO extends GameMode {

    List<GameMap> gameMapList;

    public List<GameMap> getGameMapList() {
        return gameMapList;
    }

    public void setGameMapList(List<GameMap> gameMapList) {
        this.gameMapList = gameMapList;
    }
}
