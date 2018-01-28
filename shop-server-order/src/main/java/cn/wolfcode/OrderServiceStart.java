package cn.wolfcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("cn.wolfcode.shop.mapper")
@EnableTransactionManagement
public class OrderServiceStart {
    public static void main(String[] args) {
	    SpringApplication.run(OrderServiceStart.class, args);
    }
}
