package cn.wolfcode.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 商品分类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catalog extends BaseDomain{
    private Date createDate;//分类创建日期
    private Date lastModifiedDate;//分类修改日期
    private Integer version;//版本
    private Integer level;//等级
    private String name;//分类名称
    private String code;//编码
    private Integer sequence=1;//排序
    private Integer childrenCatalogs;//子分类
    private Integer products;//商品
    private Long parentCatalogId;//父分类
    private Integer isParent;//是不是父类
}
