package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class User implements Serializable{
    private Long id;//用户id

    private String nickName;//用户昵称

    private String account;//用户账号

    private String password;//用户密码

    private String phone;//用户电话

    private String email;//邮箱地址

    private Boolean sex;//性别

    private Date birthday;//生日

    private BigDecimal userMoney;//用户余额

    private String grade;//分数

    private Integer experience;//经验值

    private Long integral;//积分

    private String username;
}