package com.example.SPRING_BOOT_MYSQL_CRUD.controllers;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Block;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Room;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.BlockService;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.RoomService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/block")
public class BlockController {
    @Autowired
    BlockService blockService = new BlockService();
    @Autowired
    RoomService roomService =  new RoomService();

    @GetMapping("/get")
    List<Block> getBlocks(){
        return blockService.getAllBlocks();
    }
    @GetMapping("/get/{id}")
    Optional<Block> getBockById(@PathVariable UUID id){
        return blockService.findById(id);
    }
    @PostMapping("/add")
    Block addBlock(@RequestBody Block block){
        return blockService.save(block);
    }
    @PutMapping("/edit/{id}")
    Block editBlock(@PathVariable UUID id, @RequestBody Block block){
        Optional<Block> blockOptional = blockService.findById(id);
        if(blockOptional.isPresent()){
            blockOptional.get().setName(block.getName());
            blockOptional.get().setCapacity(block.getCapacity());
            return blockService.save(blockOptional.get());
        }else{
            return null;
        }
    }
    @GetMapping("/get/room/{id}")
    Optional<List<Room>> getRoomsByBlockId(@PathVariable UUID id){
        Optional<Block> blockOptional = blockService.findById(id);
        return Optional.ofNullable(blockOptional.get().getRooms());
    }
    @Transactional
    @GetMapping("/get/block/{blockId}/room/{roomId}")
    Optional<Room> getRoomByBlockId(@PathVariable UUID blockId, @PathVariable UUID roomId){
        Optional<Block> blockOptional = blockService.findById(blockId);
        return blockOptional.map(block -> block.getRooms().stream().filter(room -> room.getId().equals(roomId)).findFirst().get());
    }
    @PostMapping("/add/{blockId}/room")
    Room addRoom(@PathVariable UUID blockId, @RequestBody Room room){
        Optional<Block> blockOptional = blockService.findById(blockId);
        if(blockOptional.isPresent()){
            blockOptional.get().getRooms().add(room);
            room.setBlock(blockOptional.get());
            roomService.save(room);
            return room;
        }else{
            return null;
        }
    }
    @Transactional
    @GetMapping("/get/foyer")
    Map<Foyer,Block> getFoyersByBlockId(){
        Map<Foyer,Block> foyerMap = new HashMap<>();
        List<Block> blocks = blockService.getAllBlocks();
        blocks.forEach(block -> foyerMap.put(block.getFoyer(), block));
        return foyerMap;
    }
    @Transactional
    @GetMapping("/get/block/{blockId}/foyer")
    Optional<Foyer> getFoyerByBlockId(@PathVariable UUID blockId){
        Optional<Block> blockOptional = blockService.findById(blockId);
        return blockOptional.map(Block::getFoyer);
    }
    @PutMapping("/edit/block/{blockId}/room/{roomId}")
    public Room editRoomViaBlockId(@PathVariable UUID blockId, @PathVariable UUID roomId,@RequestBody Room room){
        Optional<Block> blockOptional = blockService.findById(blockId);
        if(blockOptional.isPresent()){
            Optional<Room> foundRoom = roomService.findRoomById(roomId);
            if(foundRoom.isPresent()){
                foundRoom.get().setTypeR(room.getTypeR());
                foundRoom.get().setRoomNumber(room.getRoomNumber());
                return roomService.save(foundRoom.get());
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
