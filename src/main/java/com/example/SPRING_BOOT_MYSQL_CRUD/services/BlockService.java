package com.example.SPRING_BOOT_MYSQL_CRUD.services;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Block;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Room;
import com.example.SPRING_BOOT_MYSQL_CRUD.repositories.BlockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService {
    @Autowired
    BlockRepo blockRepo;

    Optional<Foyer> findFoyerByBlockId(Long blockId) {
        Optional<Block> foundBlock = blockRepo.findBlockById(blockId);
        return foundBlock.map(Block::getFoyer);
    };
    List<Room> findRoomsByBlockId(Long blockId) {
        Optional<Block> foundBlock = blockRepo.findBlockById(blockId);
        if(foundBlock.isPresent()){
            return foundBlock.get().getRooms();
        }else{
            return List.of();
        }
    }
}
