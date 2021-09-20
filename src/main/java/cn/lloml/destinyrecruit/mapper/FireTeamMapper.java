package cn.lloml.destinyrecruit.mapper;

import cn.lloml.destinyrecruit.domain.FireTeam;

import java.util.List;

import cn.lloml.destinyrecruit.dto.FireTeamSelectDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FireTeamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FireTeam record);

    int insertSelective(FireTeam record);

    FireTeam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FireTeam record);

    int updateByPrimaryKey(FireTeam record);

    List<FireTeam> selectByAll(FireTeam fireTeam);

    FireTeamSelectDTO selectDTOByPrimaryKey(Long id);

    List<FireTeamSelectDTO> selectDTO();
}