package com.budgetninja.back.saving;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/savings")
public class SavingController {
    private final SavingService savingService;

    public SavingController(SavingService savingService) {
        this.savingService = savingService;
    }

    @GetMapping("")
    public List<Saving> findAll() {
        return savingService.findAll();
    }

    @GetMapping("/{id}")
    public Saving findById(@PathVariable Long id) {
        return savingService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Saving> getSavingByUserId(@PathVariable Long userId) {
        Saving saving = savingService.findByUser(userId);
        if (saving == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Epargne non trouvée");
        }
        return ResponseEntity.ok(saving);
    }

    @PostMapping("/{userId}")
    public Saving save(@PathVariable long userId, @RequestBody Saving saving) {

        return savingService.addSavingToUser(userId, saving);
    }

    @PostMapping("programmed/{savingId}")
    public Saving addProgrammedSaving(@PathVariable long savingId, @RequestBody Saving saving) {
        return savingService.addProgrammedSaving(savingId, saving);
    }

    @PostMapping("programmedFrequency/{savingId}")
    public Saving addProgrammedSavingFrequency(@PathVariable long savingId, @RequestBody Saving saving) {
        return savingService.addProgrammedSavingFrequency(savingId, saving);
    }

    @PutMapping("/{id}")
    public Saving update(@RequestBody Saving saving, @PathVariable Long id) {
        return savingService.update(saving, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        savingService.delete(id);
    }
}
