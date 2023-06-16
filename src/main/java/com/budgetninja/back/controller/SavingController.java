package com.budgetninja.back.controller;

import com.budgetninja.back.model.SavingModel;
import com.budgetninja.back.service.SavingService;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public SavingModel save(@RequestBody SavingModel savingModel) {
        return savingService.save(savingModel);
    }

    @PutMapping("{id}")
    public SavingModel update(@RequestBody SavingModel savingModel, @PathVariable Long id) {
        return savingService.update(savingModel, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        savingService.delete(id);
    }
}
