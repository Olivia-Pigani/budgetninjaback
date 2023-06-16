package com.budgetninja.back.controller;

import com.budgetninja.back.model.NinjaModel;
import com.budgetninja.back.service.NinjaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/user")
public class NinjaController {
    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService){
        this.ninjaService = ninjaService;
    }
    @GetMapping("")
    public List<NinjaModel> getAllNinja(){
        return ninjaService.findAllNinja();
    }
    @PostMapping("")
    public NinjaModel createNinja(@RequestBody NinjaModel ninjaModel){
        return ninjaService.save(ninjaModel);
}
    @GetMapping("/{user_id}")
    public NinjaModel getNinjaById(@PathVariable Long ninja_id){
        return ninjaService.findNinjaById(ninja_id);
    }
    @DeleteMapping("{ninja_id}")
    public void deleteNinja(@PathVariable Long ninja_id){
        ninjaService.deleteNinjaById(ninja_id);
    }

}
