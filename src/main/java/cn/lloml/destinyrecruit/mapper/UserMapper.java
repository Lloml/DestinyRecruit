package cn.lloml.destinyrecruit.mapper;
import org.apache.ibatis.annotations.Param;

import cn.lloml.destinyrecruit.domain.User;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectByAll(User user);

    User selectOneByBungieName(@Param("bungieName")String bungieName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();
}