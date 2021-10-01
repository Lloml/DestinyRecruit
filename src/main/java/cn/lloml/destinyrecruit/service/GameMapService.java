package cn.lloml.destinyrecruit.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import cn.lloml.destinyrecruit.domain.GameMap;
import cn.lloml.destinyrecruit.mapper.GameMapMapper;
@Service
public class GameMapService{

    @Resource
    private GameMapMapper gameMapMapper;

    
    public int deleteByPrimaryKey(Long id) {
        return gameMapMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(GameMap record) {
        return gameMapMapper.insert(record);
    }

    
    public int insertSelective(GameMap record) {
        return gameMapMapper.insertSelective(record);
    }

    
    public GameMap selectByPrimaryKey(Long id) {
        return gameMapMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(GameMap record) {
        return gameMapMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(GameMap record) {
        return gameMapMapper.updateByPrimaryKey(record);
    }

    
    public List<GameMap> selectByAll(GameMap gameMap) {
        return gameMapMapper.selectByAll(gameMap);
    }

	public List<GameMap> selectAll(){
		 return gameMapMapper.selectAll();
	}




}
