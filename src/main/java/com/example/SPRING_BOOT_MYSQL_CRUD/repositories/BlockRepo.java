package com.example.SPRING_BOOT_MYSQL_CRUD.repositories;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Block;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlockRepo extends JpaRepository<Block, Long> {
    Optional<Block> findBlockById(Long id);
    Optional<Block> findBlockByName(String name);
    Optional<Block> findBlockByCapacity(Long capacity);
}