package com.example.SPRING_BOOT_MYSQL_CRUD.repositories;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FoyerRepo extends JpaRepository<Foyer, UUID> {
    Optional<Foyer> findFoyerByName(String name);
    Optional<Foyer> findFoyerByCapacity(Long capacity);
    Optional<Foyer> findFoyerByUniversity(University university);
}
