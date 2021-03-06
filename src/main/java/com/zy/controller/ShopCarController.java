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

/**
 * @author czy
 */
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
    public String addCommodity(@RequestBody Commodity commodity, HttpSession session){
        User user = (User) session.getAttribute("user");
        return shopCarService.addCommodity(commodity,session);
    }

    /**
     * 提交购物车
     * @param session
     * @return
     * @throws InterruptedException
     */
    @ResponseBody
    @RequestMapping("/insertsopcar")
    public String insertSopcar(HttpSession session) throws InterruptedException {
        return shopCarService.insertSopcar(session);
    }
}
