package com.store.cont4bl3.repository;

import com.store.cont4bl3.model.LibroMayorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LibroMayorRepository extends JpaRepository<LibroMayorModel, Integer> {
    List<LibroMayorModel> findByCuentaIdCuentaOrderByFechaAsc(Integer idCuenta);
    LibroMayorModel findTopByCuentaIdCuentaOrderByFechaDesc(Integer idCuenta);
    List<LibroMayorModel> findByFecha(LocalDate fecha);
    List<LibroMayorModel> findByFechaBetween(LocalDate fechaInicial, LocalDate fechaFinal);
    List<LibroMayorModel> findByCuenta_IdCuentaAndFechaBetween(Integer cuentaId, LocalDate fechaInicial, LocalDate fechaFinal);

}
