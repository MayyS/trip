package cn.wolfcode.trip.service.impl;

import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.service.IUserService;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:S
 * @Date:19/6/20
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveOrUpdate(User user) {
        if(user.getId()!=null){
            userMapper.updateByPrimaryKey(user);
        }else{
            User usr=userMapper.selectByEmail(user.getEmail());
            if(usr!=null){
                throw new RuntimeException("email already exist");
            }
            userMapper.insert(user);
        }
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> listAll() {
        return userMapper.selectAll();
    }

    @Override
    public PageInfo query(QueryObject obj) {
        PageHelper.startPage(obj.getCurrentPage(),obj.getPageSize());
        List<User>users=userMapper.selectForList(obj);
        return new PageInfo(users);
    }
    public User login(String email,String pwd){
        User user=userMapper.selectByEmailAndPassword(email,pwd);
        if(user==null){
            throw new RuntimeException("error");
        }
        return user;
    }
}
