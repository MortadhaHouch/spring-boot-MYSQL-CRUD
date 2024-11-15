package com.example.SPRING_BOOT_MYSQL_CRUD.controllers;

import com.example.SPRING_BOOT_MYSQL_CRUD.models.Block;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.Foyer;
import com.example.SPRING_BOOT_MYSQL_CRUD.models.University;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.BlockService;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.FoyerService;
import com.example.SPRING_BOOT_MYSQL_CRUD.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/foyer")
public class FoyerController {
    @Autowired
    FoyerService foyerService = new FoyerService();
    @Autowired
    UniversityService universityService =  new UniversityService();
    @Autowired
    BlockService blockService =  new BlockService();
    @GetMapping("/get")
    List<Foyer> getFoyer() {
        return foyerService.findAll();
    }
    @PostMapping("/add")
    Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.saveFoyer(foyer);
    }
    @PutMapping("/edit/{id}")
    String editFoyer(@RequestBody Foyer foyer,@PathVariable UUID id) {
        Optional<Foyer> foundFoyer = foyerService.findById(id);
        if(foundFoyer.isPresent()) {
            foundFoyer.get().setName(foyer.getName());
            foundFoyer.get().setCapacity(foyer.getCapacity());
            foyerService.saveFoyer(foundFoyer.get());
            return "foyer updated successfully";
        }else{
            return "error updating foyer";
        }
    }
    @GetMapping("/{foyerId}/get-blocks")
    Optional<List<Block>> getBlocks(@PathVariable UUID foyerId) {
        return Optional.of(foyerService.findById(foyerId).get().getBlocks());
    }
    @PutMapping("/edit/foyer/{foyerId}/university/{universityId}")
    String toggleAddUniversityToFoyer(@PathVariable UUID foyerId,@PathVariable UUID universityId) {
        Optional<Foyer> foundFoyer = foyerService.findById(foyerId);
        if(foundFoyer.isPresent()) {
            Optional<University> foundUniversity = universityService.findUniversityById(universityId);
            if(foundUniversity.isPresent()) {
                if(foundFoyer.get().getUniversity().equals(foundUniversity.get())){
                    foundFoyer.get().setUniversity(null);
                    foundUniversity.get().setFoyer(null);
                    return "error removing university from foyer";
                }else{
                    foundFoyer.get().setUniversity(foundUniversity.get());
                    foundUniversity.get().setFoyer(foundFoyer.get());
                    return "successfully add university to foyer";
                }
            }else{
                return "university does not exist";
            }
        }else{
            return "foyer not found";
        }
    }
    @PutMapping("/edit/foyer/{foyerId}/block/{blockId}")
    String toggleAddBlockToFoyer(@PathVariable UUID foyerId,@PathVariable UUID blockId) {
        Optional<Foyer> foundFoyer = foyerService.findById(foyerId);
        if(foundFoyer.isPresent()) {
            Optional<Block> foundBlock = blockService.findById(blockId);
            if(foundBlock.isPresent()) {
                if(foundFoyer.get().getBlocks().contains(foundBlock.get())){
                    foundFoyer.get().getBlocks().remove(foundBlock.get());
                    foundBlock.get().setFoyer(null);
                    foyerService.saveFoyer(foundFoyer.get());
                    blockService.save(foundBlock.get());
                    return "successfully removed block from foyer";
                }else{
                    foundFoyer.get().getBlocks().add(foundBlock.get());
                    foundBlock.get().setFoyer(foundFoyer.get());
                    foyerService.saveFoyer(foundFoyer.get());
                    blockService.save(foundBlock.get());
                    return "successfully added block to foyer";
                }
            }else{
                return "block not found";
            }
        }else{
            return "foyer not found";
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteFoyer(@PathVariable UUID id) {
        Optional<Foyer> foundFoyer = foyerService.findById(id);
        if(foundFoyer.isPresent()) {
            return foyerService.deleteFoyer(id);
        }else {
            return "error deleting foyer";
        }
    }
}
