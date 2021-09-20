package cn.lloml.destinyrecruit.mapper;

import cn.lloml.destinyrecruit.domain.GameMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameMapMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameMap record);

    int insertSelective(GameMap record);

    GameMap selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameMap record);

    int updateByPrimaryKey(GameMap record);

    List<GameMap> selectByAll(GameMap gameMap);
}