package com.zy.mapper;


import com.zy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component(value = "userMapper")
public interface UserMapper {
    public User selectUser(String name);
}
