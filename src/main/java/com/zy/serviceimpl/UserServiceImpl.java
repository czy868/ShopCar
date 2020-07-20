package com.zy.serviceimpl;

import com.zy.entity.User;
import com.zy.mapper.UserMapper;
import com.zy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author czy
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * @param user
     * @return
     */
    @Override
    public User selectUser(User user) {
        User u=userMapper.selectUser(user.getName());
        return u;
    }
}
