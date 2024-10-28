package com.example.SPRING_BOOT_MYSQL_CRUD.repositories;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UniversityRepo extends JpaRepository<University, UUID> {
    Optional<University> findUniversityByFoyer(Foyer foyer);
    Optional<University> findUniversityByName(String universityName);
    Optional<University> findUniversityById(UUID id);
    List<University> findUniversitiesByAddress(String address);
}