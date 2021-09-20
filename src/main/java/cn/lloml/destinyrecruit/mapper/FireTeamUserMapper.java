package cn.lloml.destinyrecruit.mapper;
import org.apache.ibatis.annotations.Param;

import cn.lloml.destinyrecruit.domain.FireTeamUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FireTeamUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FireTeamUser record);

    int insertSelective(FireTeamUser record);

    FireTeamUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FireTeamUser record);

    int updateByPrimaryKey(FireTeamUser record);


    int updateOwnerByFireTeamIdAndUserId(@Param("updatedOwner")Boolean updatedOwner,@Param("fireTeamId")Long fireTeamId,@Param("userId")Long userId);


    FireTeamUser selectOneByUserId(@Param("userId")Long userId);

    int deleteByFireTeamId(@Param("fireTeamId")Long fireTeamId);


}