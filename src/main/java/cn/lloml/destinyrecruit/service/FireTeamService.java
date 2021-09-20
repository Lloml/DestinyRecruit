package cn.lloml.destinyrecruit.service;

import cn.lloml.destinyrecruit.domain.FireTeamUser;
import cn.lloml.destinyrecruit.dto.FireTeamInsertDTO;
import cn.lloml.destinyrecruit.dto.FireTeamSelectDTO;
import cn.lloml.destinyrecruit.mapper.FireTeamUserMapper;
import cn.lloml.destinyrecruit.util.SnowflakeIdWorker;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.lloml.destinyrecruit.domain.FireTeam;
import cn.lloml.destinyrecruit.mapper.FireTeamMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FireTeamService {

    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    @Resource
    private FireTeamMapper fireTeamMapper;

    @Resource
    private FireTeamUserMapper fireTeamUserMapper;

    private FireTeamSelectDTO setOwnerOfFireTeamDTO(FireTeamSelectDTO fireTeamSelectDTO) {
        if (fireTeamSelectDTO.getMemberList() == null) {
            return fireTeamSelectDTO;
        }
        fireTeamSelectDTO.getMemberList().forEach(member -> {
            if (member.isOwner()) {
                fireTeamSelectDTO.setOwner(member);
            }
        });
        return fireTeamSelectDTO;
    }


    public int deleteByPrimaryKey(Long id) {
        //同时删除与之对应的关系
        fireTeamUserMapper.deleteByFireTeamId(id);
        return fireTeamMapper.deleteByPrimaryKey(id);
    }


    public int insert(FireTeam record) {
        return fireTeamMapper.insert(record);
    }

    public int userCreateFireTeamByDTO(FireTeamInsertDTO fireTeamInsertDTO, Long userId) {
        var newFireTeam = new FireTeam();
        var newFireTeamId = snowflakeIdWorker.nextId();
        //创建火力战队
        newFireTeam.setTitle(fireTeamInsertDTO.getTitle());
        newFireTeam.setDescription(fireTeamInsertDTO.getDescription());
        newFireTeam.setGameMapId(fireTeamInsertDTO.getGameMapId());
        newFireTeam.setId(newFireTeamId);
        newFireTeam.setCreatedDate(new Date());
        newFireTeam.setUpdateDate(new Date());
        var resultNumber = fireTeamMapper.insert(newFireTeam);
        //用户作为拥有者加入火力战队
        pushUserToFireTeam(newFireTeamId, userId, true);

        return resultNumber;
    }

    public int insertSelective(FireTeam record) {
        return fireTeamMapper.insertSelective(record);
    }


    public FireTeam selectByPrimaryKey(Long id) {
        return fireTeamMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(FireTeam record) {
        return fireTeamMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(FireTeam record) {
        return fireTeamMapper.updateByPrimaryKey(record);
    }

    public List<FireTeam> selectByAll(FireTeam fireTeam) {
        if (fireTeam == null) {
            fireTeam = new FireTeam();
        }
        return fireTeamMapper.selectByAll(fireTeam);
    }

    public FireTeamSelectDTO selectOneDtoPrimaryKey(Long id) {
        var fireTeam = fireTeamMapper.selectDTOByPrimaryKey(id);
        if (fireTeam == null) {
            return null;
        }
        return setOwnerOfFireTeamDTO(fireTeam);
    }

    public List<FireTeamSelectDTO> selectDTO() {
        var fireTeamDTOList = fireTeamMapper.selectDTO();
        var resultFireTeamDTOList = new ArrayList<FireTeamSelectDTO>();
        fireTeamDTOList.forEach(fireTeamSelectDTO -> {
            fireTeamSelectDTO.getMemberList().forEach(member -> {
                if (member.isOwner()) {
                    fireTeamSelectDTO.setOwner(member);
                }
            });
            resultFireTeamDTOList.add(fireTeamSelectDTO);
        });
        return resultFireTeamDTOList;
    }

    public FireTeamSelectDTO selectOneDTOByUserId(Long userId) {
        var userAndFireTeamRelationship = fireTeamUserMapper.selectOneByUserId(userId);
        if (userAndFireTeamRelationship == null) {
            return null;
        } else {
            return setOwnerOfFireTeamDTO(
                    fireTeamMapper.selectDTOByPrimaryKey(userAndFireTeamRelationship.getFireTeamId())
            );
        }
    }

    public void pushUserToFireTeam(Long fireTeamId, Long userId, Boolean owner) {
        var userAndFireTeamRelationship = new FireTeamUser();
        userAndFireTeamRelationship.setId(snowflakeIdWorker.nextId());
        userAndFireTeamRelationship.setUserId(userId);
        userAndFireTeamRelationship.setFireTeamId(fireTeamId);
        userAndFireTeamRelationship.setOwner(owner);
        fireTeamUserMapper.insert(userAndFireTeamRelationship);
    }

    public void updateFireTeamMemberIsOwner(Boolean isOwner, Long fireTeamId, Long memberId){
        fireTeamUserMapper.updateOwnerByFireTeamIdAndUserId(isOwner,fireTeamId,memberId);
    }

    public void deleteUserFromFireTeam(Long fireTeamId, Long userId) {
        var userAndFireTeamRelationship = fireTeamUserMapper.selectOneByUserId(userId);
        fireTeamUserMapper.deleteByPrimaryKey(userAndFireTeamRelationship.getId());
    }
}




