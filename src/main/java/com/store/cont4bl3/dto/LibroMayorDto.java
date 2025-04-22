package com.store.cont4bl3.dto;

import com.store.cont4bl3.model.CuentaContableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroMayorDto {

    private Integer idLibroMayor;


    private LocalDate fecha;

    private double saldoAnterior;


    private double debe;


    private double haber;


    private double saldoActual;


    private Integer cuenta;
}
