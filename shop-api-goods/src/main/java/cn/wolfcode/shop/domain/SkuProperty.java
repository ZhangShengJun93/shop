package cn.wolfcode.shop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkuProperty extends BaseDomain {

    private String name; //属性名称

    private Long catalogId;//所属分类

    private Byte type;//属性类型(下拉框/输入框)

    private Integer sequence; //排序

    public String getPropertyType() {
        switch (type) {
            case 0:
                return "输入框";
            case 1:
                return "多选框";
            case 2:
                return "下拉列表";
        }
        return "异常类型";
    }

}