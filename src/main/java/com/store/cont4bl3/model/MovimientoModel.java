package com.store.cont4bl3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "movimientos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMovimiento;

    private LocalDate fechaMovimiento;
    private Double monto;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = ("id_cuenta_debe"))
    private CuentaContableModel cuentaDebe;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_haber")
    private CuentaContableModel cuentaHaber;

    @Column(name = "usuario")
    private String usuario;


}
