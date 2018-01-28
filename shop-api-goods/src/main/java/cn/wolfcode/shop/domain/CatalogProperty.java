package cn.wolfcode.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogProperty extends BaseDomain {

    private Long catalogId;

    private String name;

    private Integer sequence;

    private Byte type;

    public String getPropertyType() {
        switch (type) {
            case 1:
                return "文本";
            case 2:
                return "下拉列表";
        }
        return "未知";
    }
}