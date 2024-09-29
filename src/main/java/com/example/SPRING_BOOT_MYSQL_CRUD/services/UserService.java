package com.example.SPRING_BOOT_MYSQL_CRUD.services;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Student;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public List<Student> getUsers() {
        return userRepo.findAll();
    }
    public Optional<Student> getUserById(Long id) {
        return userRepo.findById(id);
    }
}
