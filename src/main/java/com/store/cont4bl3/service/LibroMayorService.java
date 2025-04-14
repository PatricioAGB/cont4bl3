package com.store.cont4bl3.service;

import com.store.cont4bl3.model.LibroMayorModel;
import com.store.cont4bl3.repository.LibroMayorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LibroMayorService {

    @Autowired
    LibroMayorRepository libroMayorRepository;

    public List<LibroMayorModel> findAll() {
        return libroMayorRepository.findAll();
    }

    public List<LibroMayorModel> findByCuentaIdCuenta(Integer idCuenta) {
        return libroMayorRepository.findByCuentaIdCuentaOrderByFechaAsc(idCuenta);
    }
    public LibroMayorModel findUltimoPorCuenta(Integer idCuenta) {
        return libroMayorRepository.findTopByCuentaIdCuentaOrderByFechaDesc(idCuenta);
    }
    public List<LibroMayorModel> findByFecha(LocalDate fecha) {
        return libroMayorRepository.findByFecha(fecha);
    }
    public List<LibroMayorModel> findByFechaBetween(LocalDate fechaInicial, LocalDate fechaFinal) {
        return libroMayorRepository.findByFechaBetween(fechaInicial, fechaFinal);
    }
    public List<LibroMayorModel> findByCuentaIdCuentaAndFecha(Integer idCuenta, LocalDate fechaInicial, LocalDate fechaFinal) {
        return libroMayorRepository.findByCuenta_IdCuentaAndFechaBetween(idCuenta,fechaInicial, fechaFinal );
    }

}
