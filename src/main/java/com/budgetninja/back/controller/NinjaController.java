package com.budgetninja.back.controller;


import com.budgetninja.back.model.NinjaModel;
import com.budgetninja.back.service.NinjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ninjas")

public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }


    @GetMapping("")
    public List<NinjaModel> findAll(){
        return ninjaService.findAll();
    }

    @GetMapping("{id}")
    public NinjaModel findById(@PathVariable Long id){
        return ninjaService.findById(id);
    }

    @PostMapping("")
    public NinjaModel save(@RequestBody NinjaModel ninjaModel){
        return ninjaService.update(ninjaModel);
    }

    @PutMapping("")
    public NinjaModel update(@RequestBody NinjaModel ninjaModel){
        return ninjaService.update(ninjaModel);
    }










}
