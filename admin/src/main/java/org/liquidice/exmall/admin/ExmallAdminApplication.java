package org.liquidice.exmall.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.liquidice.exmall.admin.dao.mapper")
public class ExmallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExmallAdminApplication.class, args);
    }

}
