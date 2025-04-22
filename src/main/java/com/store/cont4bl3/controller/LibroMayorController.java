package com.store.cont4bl3.controller;

import com.store.cont4bl3.model.LibroMayorModel;
import com.store.cont4bl3.service.LibroMayorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/libro-mayor")
public class LibroMayorController {

    @Autowired
    LibroMayorService libroMayorService;

    @GetMapping
    @Operation(summary = "Listar todos los registros del libro mayor", description = "Devuelve todos los registros disponibles del libro mayor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registros encontrados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<LibroMayorModel> getAll() {
        return libroMayorService.findAll();
    }

    @GetMapping("/cuenta/{idCuenta}")
    @Operation(summary = "Obtener registros del libro mayor por ID de cuenta", description = "Devuelve los registros asociados a una cuenta específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registros encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron registros para la cuenta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<LibroMayorModel> getByCuenta(
            @Parameter(description = "ID de la cuenta contable") @PathVariable Integer idCuenta) {
        return libroMayorService.findByCuentaIdCuenta(idCuenta);
    }

    @GetMapping("/cuenta/{idCuenta}/ultimo")
    @Operation(summary = "Obtener el último registro del libro mayor por cuenta", description = "Devuelve el registro más reciente para una cuenta")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registro encontrado"),
            @ApiResponse(responseCode = "404", description = "No se encontró registro para la cuenta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public LibroMayorModel getUltimoByCuenta(
            @Parameter(description = "ID de la cuenta contable") @PathVariable Integer idCuenta) {
        return libroMayorService.findUltimoPorCuenta(idCuenta);
    }

    @GetMapping("/fecha/{fecha}")
    @Operation(summary = "Buscar registros por fecha exacta", description = "Devuelve todos los registros del libro mayor en una fecha específica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registros encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron registros en esa fecha"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<LibroMayorModel> getByFecha(
            @Parameter(description = "Fecha exacta en formato yyyy-MM-dd", example = "2025-04-22")
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return libroMayorService.findByFecha(fecha);
    }

    @GetMapping("/rango")
    @Operation(summary = "Buscar registros entre dos fechas", description = "Devuelve todos los registros del libro mayor entre dos fechas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registros encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron registros en el rango"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<LibroMayorModel> getByRangoFecha(
            @Parameter(description = "Fecha de inicio del rango", example = "2025-01-01")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioFecha,
            @Parameter(description = "Fecha de término del rango", example = "2025-04-22")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaTermino) {
        return libroMayorService.findByFechaBetween(inicioFecha, fechaTermino);
    }

    @GetMapping("/cuenta/{idCuenta}/rango")
    @Operation(summary = "Buscar registros por cuenta y rango de fechas", description = "Devuelve registros del libro mayor de una cuenta específica entre dos fechas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Registros encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron registros para esa cuenta en el rango"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<LibroMayorModel> getByCuentaYRango(
            @Parameter(description = "ID de la cuenta contable") @PathVariable Integer idCuenta,
            @Parameter(description = "Fecha de inicio del rango", example = "2025-01-01")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioFecha,
            @Parameter(description = "Fecha de término del rango", example = "2025-04-22")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaTermino) {
        return libroMayorService.findByCuentaIdCuentaAndFecha(idCuenta, inicioFecha, fechaTermino);
    }
}
