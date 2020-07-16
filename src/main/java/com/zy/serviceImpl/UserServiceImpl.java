package com.zy.serviceImpl;

import com.zy.entity.User;
import com.zy.mapper.UserMapper;
import com.zy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User selectUser(User user) {
        System.out.println("name: "+user.getName());
        User u=userMapper.selectUser(user.getName());
        return u;
    }
}
