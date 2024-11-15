package com.example.SPRING_BOOT_MYSQL_CRUD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
@CrossOrigin
public class SpringBootMysqlCrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMysqlCrudApplication.class, args);
    }
}
