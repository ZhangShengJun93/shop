package cn.wolfcode.shop.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页查询
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryObject implements Serializable{
    private int currentPage=1;//当前页
    private int pageSize=10;//当前页显示条数
    public int getStartIndex(){
        return (getCurrentPage()-1)*pageSize;
    }
}

