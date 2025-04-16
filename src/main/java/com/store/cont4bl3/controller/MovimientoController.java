package com.store.cont4bl3.controller;

import com.store.cont4bl3.model.MovimientoModel;
import com.store.cont4bl3.service.MovimientoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    MovimientoService movimientoService;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Se encontraron movimientos ")
    @ApiResponse(responseCode = "404", description = "No se encontraron movimientos")
    @ApiResponse(responseCode = "500", description = "Error interono del server")
    public List<MovimientoModel> getAll(){
        return movimientoService.findAll();
    }
    @PostMapping
    @ApiResponse(responseCode = "200", description = "Se agregaron movimientos ")
    @ApiResponse(responseCode = "404", description = "No se agregaron movimientos")
    @ApiResponse(responseCode = "500", description = "Error interono del server")
    public MovimientoModel create(@RequestBody MovimientoModel movimientoModel){
        return movimientoService.save(movimientoModel);
    }
}
