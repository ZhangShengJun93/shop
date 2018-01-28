package cn.wolfcode.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCatalogPropertyValue extends BaseDomain{

    private Long productId;

    private String name;

    private String value;

}