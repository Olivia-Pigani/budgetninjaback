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

    @GetMapping("/user/{userId}")
    public Alert findByUserId(@PathVariable Long userId){
        return alertService.findByUserId(userId);
    }

    @PostMapping("/{userId}")
    public Alert addAlert(@RequestBody Alert alert, @PathVariable long userId){
        return alertService.addAlertToUser(userId, alert);
    }

    @PutMapping("/{id}")
    public Alert update(@RequestBody Alert alert, @PathVariable long id){
        return alertService.update(id, alert);
    }
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id){
        alertService.deleteById(id);
    }
}
