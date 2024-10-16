package com.example.SPRING_BOOT_MYSQL_CRUD.repositories;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Block;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlockRepo extends JpaRepository<Block, UUID> {
    List<Block> findAll();
    Optional<Block> findBlockById(UUID id);
    Optional<Block> findBlockByName(String name);
    Optional<Block> findBlockByCapacity(Long capacity);
}