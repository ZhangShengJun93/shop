package cn.wolfcode.shop.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 品牌关键字查询
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandQueryObject extends QueryObject{
    private String keyword;
}
