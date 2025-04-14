package com.store.cont4bl3.controller;

import com.store.cont4bl3.model.MovimientoModel;
import com.store.cont4bl3.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    MovimientoService movimientoService;

    @GetMapping
    public List<MovimientoModel> getAll(){
        return movimientoService.findAll();
    }
    @PostMapping
    public MovimientoModel create(@RequestBody MovimientoModel movimientoModel){
        return movimientoService.save(movimientoModel);
    }
}
