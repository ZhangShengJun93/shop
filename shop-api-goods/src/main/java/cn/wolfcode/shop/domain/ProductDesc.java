package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDesc extends BaseDomain{

    private Long productId;

    private String details;


}