package com.store.cont4bl3.controller;

import com.store.cont4bl3.model.CuentaContableModel;
import com.store.cont4bl3.service.CuentaContableService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "200", description = "Cuentas Contables encontradas")
    @ApiResponse(responseCode = "404", description = "Cuentas Contables no encontradas")
    @ApiResponse(responseCode = "500", description = "Error interno del server")
    public List<CuentaContableModel> getAll() {
        return cuentaContableService.findAll();
    }
    //endPoint para obtener las cuentas por id
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Se encontro cuenta contable con el id seleccionado")
    @ApiResponse(responseCode = "404", description = "No se encontro cuenta contable con el id seleccionado")
    @ApiResponse(responseCode = "500", description = "Error interno del server")
    public Optional<CuentaContableModel> getById(@PathVariable Integer id) {
        return cuentaContableService.findById(id);
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Cuenta contable agregada con exito")
    @ApiResponse(responseCode = "404", description = "Error al agregar una cuenta contable")
    @ApiResponse(responseCode = "500", description = "Error interno del server")
    public CuentaContableModel save(@RequestBody CuentaContableModel cuentaContableModel) {
        return cuentaContableService.save(cuentaContableModel);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Se actualizo correctamente la cuenta contable seleccionada")
    @ApiResponse(responseCode = "404", description = "Error al actualizar cuenta contable seleccionada")
    @ApiResponse(responseCode = "500", description = "Error interono del server")
    public CuentaContableModel update(@PathVariable Integer id, @RequestBody CuentaContableModel cuentaContableModel) {
        cuentaContableModel.setIdCuenta(id);
        return cuentaContableService.save(cuentaContableModel);
    }


}
