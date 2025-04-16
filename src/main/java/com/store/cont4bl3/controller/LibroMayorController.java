package com.store.cont4bl3.controller;

import com.store.cont4bl3.model.LibroMayorModel;
import com.store.cont4bl3.service.LibroMayorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "200", description = "Se encontraron registros del libro mayor")
    @ApiResponse(responseCode = "404", description = "No se encontraron registros del libro mayor")
    @ApiResponse(responseCode = "500", description = "Error interono del server")
    public List<LibroMayorModel> getAll() {
        return libroMayorService.findAll();
    }

    @GetMapping("/cuenta/{idCuenta}")
    @ApiResponse(responseCode = "200", description = "Se encontraron registros del libro mayor seleccionado")
    @ApiResponse(responseCode = "404", description = "No se encontraron registros del libro mayor seleccionado")
    @ApiResponse(responseCode = "500", description = "Error interono del server")
    public List<LibroMayorModel> getByCuenta(@PathVariable Integer idCuenta) {
        return libroMayorService.findByCuentaIdCuenta(idCuenta);
    }
    @GetMapping("/cuenta/{idCuenta}/ultimo")
    @ApiResponse(responseCode = "200", description = "Se encontraron los ultimos registros del libro mayor")
    @ApiResponse(responseCode = "404", description = "No se encontraron los ultimos registros del libro mayor")
    @ApiResponse(responseCode = "500", description = "Error interono del server")
    public LibroMayorModel getUltimoByCuenta(@PathVariable Integer idCuenta) {
        return libroMayorService.findUltimoPorCuenta(idCuenta);
    }
    @GetMapping("/fecha/{fecha}")
    @ApiResponse(responseCode = "200", description = "Se encontraron registros del libro mayor en la fecha seleccionada")
    @ApiResponse(responseCode = "404", description = "No se encontraron registros del libro mayor en la fecha seleccionada")
    @ApiResponse(responseCode = "500", description = "Error interono del server")
    public List<LibroMayorModel> getByFecha(@PathVariable LocalDate fecha) {
        return libroMayorService.findByFecha(fecha);
    }
    @GetMapping("/rango")
    @ApiResponse(responseCode = "200", description = "Se encontraron registros del libro mayor entre las fechas seleccionadas")
    @ApiResponse(responseCode = "404", description = "No se encontraron registros del libro mayor entre las fechas seleccionadas")
    @ApiResponse(responseCode = "500", description = "Error interono del server")
    public List<LibroMayorModel> getByRangoFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioFecha,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaTermino) {
        return libroMayorService.findByFechaBetween(inicioFecha,fechaTermino);
    }
    @GetMapping("/cuenta/{idCuenta}/rango")
    @ApiResponse(responseCode = "200", description = "Se encontraron registros del libro mayor seleccionado entre las fechas mencionadas")
    @ApiResponse(responseCode = "404", description = "No se encontraron registros del libro mayor seleccionado entre las fechas mencionadas")
    @ApiResponse(responseCode = "500", description = "Error interono del server")
    public List<LibroMayorModel> getByCuentaYRango(
            @PathVariable Integer idCuenta,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioFecha,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaTermino) {
        return libroMayorService.findByCuentaIdCuentaAndFecha(idCuenta, inicioFecha, fechaTermino);
    }
}
