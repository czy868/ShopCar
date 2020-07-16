package com.zy.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class User {
    @NotEmpty(message = "用户名不可为空")
    private String name;
    @NotEmpty(message = "密码不可为空")
    private String password;
    private String Sector;
}
