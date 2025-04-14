package com.store.cont4bl3.controller;

import com.store.cont4bl3.model.CuentaContableModel;
import com.store.cont4bl3.service.CuentaContableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaContableController {

    @Autowired
    CuentaContableService cuentaContableService;

    //endPoint para buscar todas las cuentas
    @GetMapping
    public List<CuentaContableModel> getAll() {
        return cuentaContableService.findAll();
    }
    //endPoint para obtener las cuentas por id
    @GetMapping("/{id}")
    public Optional<CuentaContableModel> getById(@PathVariable Integer id) {
        return cuentaContableService.findById(id);
    }

    @PostMapping
    public CuentaContableModel save(@RequestBody CuentaContableModel cuentaContableModel) {
        return cuentaContableService.save(cuentaContableModel);
    }

    @PutMapping("/{id}")
    public CuentaContableModel update(@PathVariable Integer id, @RequestBody CuentaContableModel cuentaContableModel) {
        cuentaContableModel.setIdCuenta(id);
        return cuentaContableService.save(cuentaContableModel);
    }


}
