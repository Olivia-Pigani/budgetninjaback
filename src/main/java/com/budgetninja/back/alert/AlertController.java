package com.budgetninja.back.alert;


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
    public List<Alert> findAll(){
        return alertService.findAll();
    }

    @GetMapping("{id}")
    public Alert findById(@PathVariable Long id){
        return alertService.findById(id);
    }

    @PostMapping("")
    public Alert save(@RequestBody Alert ingrediant){
        return alertService.update(ingrediant);
    }

    @PutMapping("")
    public Alert update(@RequestBody Alert ingrediant){
        return alertService.update(ingrediant);
    }














}
