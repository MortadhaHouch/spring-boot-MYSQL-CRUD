package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Block;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Room;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.BlockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BlockService {
    @Autowired
    BlockRepo blockRepo;
    public Optional<Foyer> findFoyerByBlockId(UUID blockId) {
        Optional<Block> foundBlock = blockRepo.findBlockById(blockId);
        return foundBlock.map(Block::getFoyer);
    };
    public Optional<List<Room>> findRoomsByBlockId(UUID blockId) {
        Optional<Block> foundBlock = blockRepo.findBlockById(blockId);
        return Optional.ofNullable(foundBlock.get().getRooms());
    }
    public Optional<Block> findById(UUID id){
        return blockRepo.findById(id);
    }
}