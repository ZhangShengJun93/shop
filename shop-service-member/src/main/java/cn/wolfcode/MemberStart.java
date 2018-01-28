package cn.wolfcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.wolfcode.shop.mapper")
public class MemberStart {
    public static void main(String[] args) {
        SpringApplication.run(MemberStart.class, args);
    }
}
