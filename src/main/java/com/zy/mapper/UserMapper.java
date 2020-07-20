package com.zy.mapper;


import com.zy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author czy
 */
@Component(value = "userMapper")
public interface UserMapper {
    /**
     * 登录验证
     * @param name
     * @return
     */
    public User selectUser(String name);
}
