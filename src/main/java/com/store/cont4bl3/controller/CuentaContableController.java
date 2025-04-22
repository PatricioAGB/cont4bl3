package com.store.cont4bl3.controller;

import com.store.cont4bl3.dto.CuentaContableDto;
import com.store.cont4bl3.model.CuentaContableModel;
import com.store.cont4bl3.service.CuentaContableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaContableController {

    @Autowired
    CuentaContableService cuentaContableService;


    //endPoint para buscar todas las cuentas
    @GetMapping
    @Operation(summary = "Obtener todas las cuentas contables", description = "Devuelve una lista de todas las cuentas registradas")
    @ApiResponse(responseCode = "200", description = "Cuentas Contables encontradas")
    @ApiResponse(responseCode = "404", description = "Cuentas Contables no encontradas")
    @ApiResponse(responseCode = "500", description = "Error interno del server")
    public Page<CuentaContableDto> getAll(Pageable pageable) {
        return cuentaContableService.findAll(pageable);
    }
    //endPoint para obtener las cuentas por id
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cuenta contable por ID", description = "Busca y devuelve una cuenta contable por su identificador único")
    @ApiResponse(responseCode = "200", description = "Se encontro cuenta contable con el id seleccionado")
    @ApiResponse(responseCode = "404", description = "No se encontro cuenta contable con el id seleccionado")
    @ApiResponse(responseCode = "500", description = "Error interno del server")
    public Optional<CuentaContableModel> getById(@PathVariable Integer id) {
        return cuentaContableService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva cuenta contable", description = "Guarda una nueva cuenta contable en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta contable registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public CuentaContableDto save(@Valid @RequestBody CuentaContableDto cuentaDto) {
        return cuentaContableService.save(cuentaDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cuenta contable existente", description = "Actualiza los datos de una cuenta contable por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta contable actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "404", description = "Cuenta contable no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public CuentaContableDto update(
            @Parameter(description = "ID de la cuenta contable a actualizar") @PathVariable Integer id,
            @Valid @RequestBody CuentaContableDto cuentaDto) {
        cuentaDto.setIdCuenta(id);
        return cuentaContableService.save(cuentaDto);
    }


}
