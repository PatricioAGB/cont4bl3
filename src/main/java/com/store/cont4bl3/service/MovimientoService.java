package com.store.cont4bl3.service;

import com.store.cont4bl3.model.LibroMayorModel;
import com.store.cont4bl3.model.MovimientoModel;
import com.store.cont4bl3.repository.LibroMayorRepository;
import com.store.cont4bl3.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    LibroMayorRepository libroMayorRepository;

    //Buscar todos lod movimientos
    public List<MovimientoModel> findAll() {
        return movimientoRepository.findAll();
    }

    public MovimientoModel save(MovimientoModel movimientoModel) {
        //Guardar Movimiento
        movimientoModel.setFechaMovimiento(LocalDate.now());
        MovimientoModel saved = movimientoRepository.save(movimientoModel);

        //Actualizar Libro mayor para la cuenta Debe
        actualizarLibroMayor(
                saved.getCuentaDebe().getIdCuenta(),
                saved.getMonto(),
                0, //haber
                saved.getDescripcion()
        );

        //Actualizar Libro mayor para la cuenta haber
        actualizarLibroMayor(
          saved.getCuentaHaber().getIdCuenta(),
          0, //debe
          saved.getMonto(),
          saved.getDescripcion()
        );
        return saved;
    }

    private void actualizarLibroMayor(Integer idCuenta, double debe,double haber, String descripcion) {
    LibroMayorModel ultimoLibroMayor = libroMayorRepository.findTopByCuentaIdCuentaOrderByFechaDesc(idCuenta);
    double saldoAnterior = (ultimoLibroMayor != null) ? ultimoLibroMayor.getSaldoActual():0;
    double saldoActual = saldoAnterior + debe - haber ;

    LibroMayorModel nuevoRegistro = new LibroMayorModel();
    nuevoRegistro.setCuenta(ultimoLibroMayor != null ? ultimoLibroMayor.getCuenta() : null);
    nuevoRegistro.setFecha(LocalDate.now());
    nuevoRegistro.setSaldoAnterior(saldoAnterior);
    nuevoRegistro.setDebe(debe);
    nuevoRegistro.setHaber(haber);
    nuevoRegistro.setSaldoActual(saldoActual);

    libroMayorRepository.save(nuevoRegistro);

    }
}
