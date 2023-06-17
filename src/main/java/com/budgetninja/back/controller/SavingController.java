package com.budgetninja.back.controller;

import com.budgetninja.back.model.SavingModel;
import com.budgetninja.back.service.SavingService;
import jakarta.persistence.Table;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/savings")
public class SavingController {
    private final SavingService savingService;

    public SavingController(SavingService savingService) {
        this.savingService = savingService;
    }

    @GetMapping("")
    public List<SavingModel> findAll() {
        return savingService.findAll();
    }

    @GetMapping("{id}")
    public SavingModel findById(@PathVariable Long id) {
        return savingService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<SavingModel> getSavingByUserId(@PathVariable Long userId) {
        SavingModel saving = savingService.findByUser(userId);
        if (saving == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Epargne non trouvée");
        }
        return ResponseEntity.ok(saving);
    }

    @PostMapping("/{userId}")
    public SavingModel save(@PathVariable long userId,  @RequestBody SavingModel savingModel) {

        return savingService.addSavingToUser(userId, savingModel);
    }

    @PutMapping("{id}")
    public SavingModel update(@RequestBody SavingModel savingModel, @PathVariable Long id) {
        return savingService.update(savingModel, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        savingService.delete(id);
    }
}
