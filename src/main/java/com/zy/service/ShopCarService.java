package com.zy.service;

import com.zy.entity.Commodity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

public interface ShopCarService {
    public String insertsopcar(HttpSession session) throws InterruptedException;
    public String addcommodity(Commodity commodity, HttpSession session);
}
