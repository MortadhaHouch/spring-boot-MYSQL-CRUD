package com.example.springbootmysqlcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootMysqlCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMysqlCrudApplication.class, args);
    }
}
