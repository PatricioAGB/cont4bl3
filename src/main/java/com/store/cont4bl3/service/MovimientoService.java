package com.store.cont4bl3.service;

import com.store.cont4bl3.dto.MovimientoDto;
import com.store.cont4bl3.model.CuentaContableModel;
import com.store.cont4bl3.model.LibroMayorModel;
import com.store.cont4bl3.model.MovimientoModel;
import com.store.cont4bl3.repository.CuentaContableRepository;
import com.store.cont4bl3.repository.LibroMayorRepository;
import com.store.cont4bl3.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    LibroMayorRepository libroMayorRepository;

    @Autowired
    CuentaContableRepository cuentaContableRepository;

    // Buscar todos los movimientos
    public List<MovimientoDto> findAll() {
        return movimientoRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Guardar Movimiento desde DTO
    public MovimientoDto save(MovimientoDto dto) {
        MovimientoModel model = new MovimientoModel();

        model.setIdMovimiento(generarIdMovimiento());
        model.setFechaMovimiento(LocalDate.now());
        model.setMonto(dto.getMonto());
        model.setDescripcion(dto.getDescripcion());
        model.setUsuario(dto.getUsuario());

        // Asignar relaciones de cuenta
        CuentaContableModel cuentaDebe = cuentaContableRepository.findById(dto.getCuentaDebe())
                .orElseThrow(() -> new RuntimeException("Cuenta debe no encontrada"));
        CuentaContableModel cuentaHaber = cuentaContableRepository.findById(dto.getCuentaHaber())
                .orElseThrow(() -> new RuntimeException("Cuenta haber no encontrada"));

        model.setCuentaDebe(cuentaDebe);
        model.setCuentaHaber(cuentaHaber);

        MovimientoModel saved = movimientoRepository.save(model);

        // Actualizar libro mayor para cada cuenta
        actualizarLibroMayor(cuentaDebe, saved.getMonto(), 0, saved.getDescripcion());
        actualizarLibroMayor(cuentaHaber, 0, saved.getMonto(), saved.getDescripcion());

        return toDto(saved);
    }

    private void actualizarLibroMayor(CuentaContableModel cuenta, double debe, double haber, String descripcion) {
        LibroMayorModel ultimoLibroMayor = libroMayorRepository.findTopByCuentaIdCuentaOrderByFechaDesc(cuenta.getIdCuenta());
        double saldoAnterior = (ultimoLibroMayor != null) ? ultimoLibroMayor.getSaldoActual() : cuenta.getSaldoInicial();
        double saldoActual;

        switch (cuenta.getTipoCuenta().toLowerCase()) {
            case "activo", "gastos" -> saldoActual = saldoAnterior + debe - haber;
            case "pasivo", "patrimonio", "ingresos" -> saldoActual = saldoAnterior - debe + haber;
            default -> saldoActual = saldoAnterior;
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

    // Generar ID entre 15 y 30 dígitos
    private BigInteger generarIdMovimiento() {
        int length = new Random().nextInt(16) + 15; // 15-30
        StringBuilder sb = new StringBuilder();
        sb.append((int) (Math.random() * 9) + 1); // Primer dígito distinto de 0
        for (int i = 1; i < length; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return new BigInteger(sb.toString());
    }

    // Mapear modelo a DTO
    private MovimientoDto toDto(MovimientoModel model) {
        MovimientoDto dto = new MovimientoDto();
        dto.setIdMovimiento(model.getIdMovimiento());
        dto.setFechaMovimiento(model.getFechaMovimiento());
        dto.setMonto(model.getMonto());
        dto.setDescripcion(model.getDescripcion());
        dto.setUsuario(model.getUsuario());
        dto.setCuentaDebe(model.getCuentaDebe().getIdCuenta());
        dto.setCuentaHaber(model.getCuentaHaber().getIdCuenta());
        return dto;
    }
}
