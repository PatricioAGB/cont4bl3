package com.store.cont4bl3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table (name = "libro_mayor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroMayorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibroMayor;

    private LocalDate fecha;
    private double saldoAnterior;
    private double debe;
    private double haber;
    private double saldoActual;

    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private CuentaContableModel cuenta;
}
