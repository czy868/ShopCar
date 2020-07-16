package com.zy.entity;

import lombok.Data;

import java.util.List;

@Data
public class Car {
    private String singleNumber;
    //单号
    private String applicationSector;
    //申请部门
    private String applicationData;
    //申请时间
    private String applicationName;
    //申请人
    private String productName;
    //商品名字
    private String specification;
    //规格
    private int quantity;
    //数量
    private String address;
    //地址
    private String needsTime;
    //需要时间
    private int total;
    //合计

}
