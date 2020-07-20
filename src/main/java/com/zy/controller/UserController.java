package com.zy.controller;

import com.zy.entity.User;
import com.zy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

/**
 * @author czy
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 登录
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public User login(@RequestBody User user, HttpSession session){
        User user1=userService.selectUser((User) user);
        session.setAttribute("user",user1);
        return user1;
    }
}
