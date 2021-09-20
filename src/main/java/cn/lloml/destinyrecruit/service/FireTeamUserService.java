package cn.lloml.destinyrecruit.service;
import java.util.List;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.lloml.destinyrecruit.domain.FireTeamUser;
import cn.lloml.destinyrecruit.mapper.FireTeamUserMapper;

@Service
public class FireTeamUserService {

    @Resource
    private FireTeamUserMapper fireTeamUserMapper;


    public int deleteByPrimaryKey(Long id) {
        return fireTeamUserMapper.deleteByPrimaryKey(id);
    }


    public int insert(FireTeamUser record) {
        return fireTeamUserMapper.insert(record);
    }


    public int insertSelective(FireTeamUser record) {
        return fireTeamUserMapper.insertSelective(record);
    }


    public FireTeamUser selectByPrimaryKey(Long id) {
        return fireTeamUserMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(FireTeamUser record) {
        return fireTeamUserMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(FireTeamUser record) {
        return fireTeamUserMapper.updateByPrimaryKey(record);
    }

	public FireTeamUser selectOneByUserId(Long userId){
		 return fireTeamUserMapper.selectOneByUserId(userId);
	}

	public int deleteByFireTeamId(Long fireTeamId){
		 return fireTeamUserMapper.deleteByFireTeamId(fireTeamId);
	}

	public int updateOwnerByFireTeamIdAndUserId(Boolean updatedOwner,Long fireTeamId,Long userId){
		 return fireTeamUserMapper.updateOwnerByFireTeamIdAndUserId(updatedOwner,fireTeamId,userId);
	}















}

