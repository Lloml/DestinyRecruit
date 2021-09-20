package cn.lloml.destinyrecruit.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lloml.destinyrecruit.mapper.GameModeMapper;
import java.util.List;
import cn.lloml.destinyrecruit.domain.GameMode;
@Service
public class GameModeService{

    @Resource
    private GameModeMapper gameModeMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return gameModeMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(GameMode record) {
        return gameModeMapper.insert(record);
    }

    
    public int insertSelective(GameMode record) {
        return gameModeMapper.insertSelective(record);
    }

    
    public GameMode selectByPrimaryKey(Long id) {
        return gameModeMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(GameMode record) {
        return gameModeMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(GameMode record) {
        return gameModeMapper.updateByPrimaryKey(record);
    }

    
    public List<GameMode> selectByAll(GameMode gameMode) {
        return gameModeMapper.selectByAll(gameMode);
    }

}
