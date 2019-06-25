package cn.wolfcode.trip.service;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Auther:S
 * @Date:19/6/20
 */
public interface IUserService {

    void saveOrUpdate(User user)throws Exception;
    void delete(Long id);
    User get(Long id);
    List<User>listAll();
    PageInfo query(QueryObject obj);
    User login(String email,String pwd)throws Exception;

}
