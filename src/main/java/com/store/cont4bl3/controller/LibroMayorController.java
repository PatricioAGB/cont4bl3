package com.store.cont4bl3.controller;

import com.store.cont4bl3.model.LibroMayorModel;
import com.store.cont4bl3.service.LibroMayorService;
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
    public List<LibroMayorModel> getAll() {
        return libroMayorService.findAll();
    }
    @GetMapping("/cuenta/{idCuenta}")
    public List<LibroMayorModel> getByCuenta(@PathVariable Integer idCuenta) {
        return libroMayorService.findByCuentaIdCuenta(idCuenta);
    }
    @GetMapping("/cuenta/{idCuenta}/ultimo")
    public LibroMayorModel getUltimoByCuenta(@PathVariable Integer idCuenta) {
        return libroMayorService.findUltimoPorCuenta(idCuenta);
    }
    @GetMapping("/fecha/{fecha}")
    public List<LibroMayorModel> getByFecha(@PathVariable LocalDate fecha) {
        return libroMayorService.findByFecha(fecha);
    }
    @GetMapping("/rango")
    public List<LibroMayorModel> getByRangoFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioFecha,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaTermino) {
        return libroMayorService.findByFechaBetween(inicioFecha,fechaTermino);
    }
    @GetMapping("/cuenta/{idCuenta}/rango")
    public List<LibroMayorModel> getByCuentaYRango(
            @PathVariable Integer idCuenta,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicioFecha,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaTermino) {
        return libroMayorService.findByCuentaIdCuentaAndFecha(idCuenta, inicioFecha, fechaTermino);
    }
}
