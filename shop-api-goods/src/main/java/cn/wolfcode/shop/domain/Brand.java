package cn.wolfcode.shop.domain;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 品牌
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseDomain {

    private Date createdDate;//品牌创建时间

    private Date lastModifiedDate;//最后修改时间

    private Integer version=0;//版本

    private Date foundDate;//品牌创建时间

    private String logo;//品牌标记

    private String chineseName;//中文名

    private String englishName;//英文名

    private String description;//品牌描述

    private String url;//品牌链接

    private Integer sequence;//排序

    private Long mods;//是否显示
    public String getJsonString(){
        //创建一个map集合
        HashMap<Object, Object> map = new HashMap<Object, Object>();
        map.put("id",id);
        map.put("chineseName",chineseName);
        map.put("url",url);
        map.put("logo",logo);
        map.put("description",description);
        map.put("sequence",sequence);
        map.put("mods",mods);
        map.put("catalogs",catalogs);
        return JSON.toJSONString(map);
    }
    //品牌与分类多对多
    private List<Catalog> catalogs = new ArrayList<Catalog>();
}