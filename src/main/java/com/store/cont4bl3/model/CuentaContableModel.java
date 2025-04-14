package com.store.cont4bl3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cuentas_contables")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaContableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuenta;

    private String nombreCuenta;
    private String tipoCuenta;
    private double saldoInicial;
}
