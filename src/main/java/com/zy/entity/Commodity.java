package com.zy.entity;

import lombok.Data;

@Data
public class Commodity {
    private String productName;
    //商品名字
    private String specification;
    //规格
    private int quantity;
    //数量
}
