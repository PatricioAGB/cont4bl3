package com.store.cont4bl3.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para representar un movimiento contable")
public class MovimientoDto {

    private BigInteger idMovimiento;


    @Schema(
            description = "Fecha en que se realiza el movimiento",
            example = "2025-04-22",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "La fecha del movimiento es obligatoria")
    private LocalDate fechaMovimiento;


    @Schema(
            description = "Monto del movimiento contable",
            example = "150000",
            minimum = "0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser un número positivo")
    private Double monto;

    @Schema(
            description = "Descripción breve del movimiento",
            example = "Compra de insumos de oficina",
            maxLength = 255
    )
    @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
    private String descripcion;

    @Schema(
            description = "ID de la cuenta contable que recibe el debe",
            example = "1001",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "La cuenta debe es obligatoria")
    private Integer cuentaDebe;


    @Schema(
            description = "ID de la cuenta contable que recibe el haber",
            example = "2001",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "La cuenta haber es obligatoria")
    private Integer cuentaHaber;

    @NotBlank(message = "El usuario no puede estar vacío")
    private String usuario;

}
