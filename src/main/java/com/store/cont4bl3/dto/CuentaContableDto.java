package com.store.cont4bl3.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para la creación o modificación de una cuenta contable")
public class CuentaContableDto {

    private Integer idCuenta;

    @Schema(description = "Nombre de la cuenta contable", example = "Caja General", maxLength = 100)
    @NotBlank(message = "El nombre de la cuenta es obligatorio")
    @Size(max = 100, message = "El nombre de la cuenta no puede tener más de 100 caracteres")
    private String nombreCuenta;

    @Schema(
            description = "Tipo de cuenta contable",
            example = "ACTIVO",
            allowableValues = {"ACTIVO", "PASIVO", "PATRIMONIO", "INGRESO", "EGRESO"}
    )
    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Pattern(regexp = "^(ACTIVO|PASIVO|PATRIMONIO|INGRESO|EGRESO)$",
            message = "El tipo de cuenta debe ser ACTIVO, PASIVO, PATRIMONIO, INGRESO o EGRESO")
    private String tipoCuenta;

    @Schema(description = "Saldo inicial de la cuenta", example = "500000.00", minimum = "0")
    @PositiveOrZero(message = "El saldo inicial no puede ser negativo")
    private double saldoInicial;

}
