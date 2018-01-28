package cn.wolfcode.shop.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductQueryObject  extends QueryObject {
    private Long[] catalogIds = new Long[]{};
    private Long brandId;
    private String keyword;
    private Long CatalogIdsFirst = -1L;
    public String getCataIds(){
        if (catalogIds.length>1){

            String cataIds = "";

            for (Long catalogId : catalogIds) {
                cataIds = cataIds+","+catalogId.toString();
            }
            String newCataIds = cataIds.substring(1);
            return newCataIds;
        }else if(catalogIds.length==1){
            return catalogIds[0].toString();
        }
        return null;
    }
}
