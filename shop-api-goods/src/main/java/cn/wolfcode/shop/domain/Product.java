package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Product implements Serializable {
    private Long id;//商品id

    private Date createdDate;//创建时间

    private Date lastModifiedDate;//最后修改时间

    private Integer version = 0;//版本号

    private Catalog catalog;//商品的所属分类

    private Brand brand;//商品的所属品牌

    private String name;//商品名称

    private String keyword;//关键字

    private String code;//商品编码

    private String image;//商品图片路径

    private BigDecimal marketPrice;//商品的市场价

    private BigDecimal basePrice;//商品的基本价

    private Long mods;//模式

    private String impressions;//商品标签，标签之间用，好分割


}