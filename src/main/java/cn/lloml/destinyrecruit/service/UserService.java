package cn.lloml.destinyrecruit.service;

import cn.lloml.destinyrecruit.util.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.lloml.destinyrecruit.mapper.UserMapper;
import cn.lloml.destinyrecruit.domain.User;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowflakeIdWorker snowflakeIdWorker;

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }



    public int insert(User record) {
        record.setId(snowflakeIdWorker.nextId());
        return userMapper.insert(record);
    }


    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }


    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User selectOneByBungieName(String bungieName){
        return  userMapper.selectOneByBungieName(bungieName);
    }


    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

}



