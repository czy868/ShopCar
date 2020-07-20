package com.zy.service;

import com.zy.entity.Commodity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

/**
 * @author czy
 */
public interface ShopCarService {
    /**
     * 提交购物车
     * @param session
     * @return
     * @throws InterruptedException
     */
    public String insertSopcar(HttpSession session) throws InterruptedException;

    /**
     * 往购物车里面提交商品
     * @param commodity
     * @param session
     * @return
     */
    public String addCommodity(Commodity commodity, HttpSession session);
}
