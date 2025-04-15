package com.store.cont4bl3.service;

import com.store.cont4bl3.model.CuentaContableModel;
import com.store.cont4bl3.model.LibroMayorModel;
import com.store.cont4bl3.model.MovimientoModel;
import com.store.cont4bl3.repository.CuentaContableRepository;
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

    @Autowired
    CuentaContableRepository cuentaContableRepository;

    //Buscar todos lod movimientos
    public List<MovimientoModel> findAll() {
        return movimientoRepository.findAll();
    }

    //Guardar Movimiento
    public MovimientoModel save(MovimientoModel movimientoModel) {
        movimientoModel.setFechaMovimiento(LocalDate.now());
        MovimientoModel saved = movimientoRepository.save(movimientoModel);

        CuentaContableModel cuentaDebe = cuentaContableRepository.findById(saved.getCuentaDebe().getIdCuenta()).orElseThrow();
        CuentaContableModel cuentaHaber = cuentaContableRepository.findById(saved.getCuentaHaber().getIdCuenta()).orElseThrow();

        //Actualizar Libro mayor para la cuenta Debe
        actualizarLibroMayor(
                cuentaDebe,
                saved.getMonto(),
                0, //haber
                saved.getDescripcion()
        );

        //Actualizar Libro mayor para la cuenta haber
        actualizarLibroMayor(
          cuentaHaber,
          0, //debe
          saved.getMonto(),
          saved.getDescripcion()
        );
        return saved;
    }

    private void actualizarLibroMayor(CuentaContableModel cuenta, double debe, double haber, String descripcion) {
        LibroMayorModel ultimoLibroMayor = libroMayorRepository.findTopByCuentaIdCuentaOrderByFechaDesc(cuenta.getIdCuenta());
        double saldoAnterior = (ultimoLibroMayor != null) ? ultimoLibroMayor.getSaldoActual() : cuenta.getSaldoInicial();
        double saldoActual;

        //Se crea para saber el tipo de cuenta y que operacion matematica usar
        switch (cuenta.getTipoCuenta().toLowerCase()){
            case "activo", "gastos" -> saldoActual = saldoAnterior + debe - haber;
            case "pasivo", "patrimonio", "ingresos" -> saldoActual = saldoAnterior - debe + haber;
            default -> saldoActual = saldoAnterior; //fallback Por si no ocurre nada
        }

        LibroMayorModel nuevoRegistro = new LibroMayorModel();
        nuevoRegistro.setCuenta(cuenta);
        nuevoRegistro.setFecha(LocalDate.now());
        nuevoRegistro.setSaldoAnterior(saldoAnterior);
        nuevoRegistro.setDebe(debe);
        nuevoRegistro.setHaber(haber);
        nuevoRegistro.setSaldoActual(saldoActual);

        libroMayorRepository.save(nuevoRegistro);
    }

}
