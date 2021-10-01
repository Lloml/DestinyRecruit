package cn.lloml.destinyrecruit.mapper;
import cn.lloml.destinyrecruit.dto.GameModeDTO;
import org.apache.ibatis.annotations.Param;

import cn.lloml.destinyrecruit.domain.GameMode;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameModeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameMode record);

    int insertSelective(GameMode record);

    GameMode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameMode record);

    int updateByPrimaryKey(GameMode record);

    List<GameMode> selectByAll(GameMode gameMode);

    List<GameModeDTO> selectDTOAll();


}