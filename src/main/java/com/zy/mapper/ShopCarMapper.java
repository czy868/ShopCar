package com.zy.mapper;

import com.zy.entity.Car;
import org.springframework.stereotype.Component;

@Component(value = "shopCarMapper")
public interface ShopCarMapper {
    public int insertsopcar(Car c);
}
