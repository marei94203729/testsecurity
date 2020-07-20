package com.ml.testsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages="com.ml.testsecurity.mapper",sqlSessionFactoryRef="firstSqlSessionFactory")
public class TestsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestsecurityApplication.class, args);
    }

}
