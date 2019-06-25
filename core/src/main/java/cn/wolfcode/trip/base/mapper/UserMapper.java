package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> selectForList(QueryObject obj);

    User selectByEmailAndPassword(@Param("email") String email, @Param("pwd") String pwd);

    User selectByEmail (String email);

}