package com.zy.controller;

import com.zy.entity.Commodity;
import com.zy.entity.User;
import com.zy.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/shopcar")
@CrossOrigin
public class ShopCarController {

    @Autowired
    ShopCarService shopCarService;

    /**
     * 向购物车里面添加商品
     * @param commodity
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public String addcommodity(@RequestBody Commodity commodity, HttpSession session){
        User user = (User) session.getAttribute("user");
        System.out.println(user.getName()+" "+user.getPassword()+" "+user.getSector());
        return shopCarService.addcommodity(commodity,session);
    }

    /**
     * 提交购物车
     * @param session
     * @return
     * @throws InterruptedException
     */
    @ResponseBody
    @RequestMapping("/insertsopcar")
    public String insertsopcar(HttpSession session) throws InterruptedException {
        return shopCarService.insertsopcar(session);
    }
}
