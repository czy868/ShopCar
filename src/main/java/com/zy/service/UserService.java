package com.zy.service;


import com.zy.entity.User;

/**
 * @author czy
 */
public interface UserService {
    /**
     * 登录验证
     * @param user
     * @return
     */
    public User selectUser(User user);
}
