package com.zy.mapper;

import com.zy.entity.Car;
import org.springframework.stereotype.Component;

/**
 * @author czy
 */
@Component(value = "shopCarMapper")
public interface ShopCarMapper {

    /**
     * 提交购物车
     * @param c
     * @return
     */
    public int insertsopcar(Car c);
}
