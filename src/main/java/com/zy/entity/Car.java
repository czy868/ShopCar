package com.zy.entity;

import lombok.Data;
import java.util.List;

/**
 * @author czy
 */
@Data
public class Car {
    //单号
    private String singleNumber;
    //申请部门
    private String applicationSector;
    //申请时间
    private String applicationData;
    //申请人
    private String applicationName;
    //商品名字
    private String productName;
    //规格
    private String specification;
    //数量
    private int quantity;
    //地址
    private String address;
    //需要时间
    private String needsTime;
    //合计
    private int total;

}
