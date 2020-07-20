package com.zy.entity;

import lombok.Data;

/**
 * @author czy
 */
@Data
public class Commodity {
    //商品名字
    private String productName;
    //规格
    private String specification;
    //数量
    private int quantity;
}
