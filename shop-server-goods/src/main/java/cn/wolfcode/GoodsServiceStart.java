package cn.wolfcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//开启事务管理
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("cn.wolfcode.shop.mapper")

public class GoodsServiceStart {

    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceStart.class, args);
    }

    
}
