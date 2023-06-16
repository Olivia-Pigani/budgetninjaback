package com.budgetninja.back.controller;


import com.budgetninja.back.model.AlertModel;
import com.budgetninja.back.service.AlertService;
import jakarta.persistence.Table;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService alertService;


    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }




    @GetMapping("")
    public List<AlertModel> findAll(){
        return alertService.findAll();
    }

    @GetMapping("{id}")
    public AlertModel findById(@PathVariable Long id){
        return alertService.findById(id);
    }

    @PostMapping("")
    public AlertModel save(@RequestBody AlertModel ingrediant){
        return alertService.update(ingrediant);
    }

    @PutMapping("")
    public AlertModel update(@RequestBody AlertModel ingrediant){
        return alertService.update(ingrediant);
    }














}
