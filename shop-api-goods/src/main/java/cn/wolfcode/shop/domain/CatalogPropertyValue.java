package cn.wolfcode.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogPropertyValue extends BaseDomain{

    private CatalogProperty catalogProperty;

    private String value;

    private Integer sequence;

    public Long getId() {
        return id;
    }
}