package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.University;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.UniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UniversityService {
    @Autowired
    UniversityRepo universityRepo;
    Optional<Foyer> findFoyerByUniversityId(int universityId) {
        Optional<University> foundUniversity = universityRepo.findById(universityId);
        return Optional.of(foundUniversity.get().getFoyer());
    }
}
