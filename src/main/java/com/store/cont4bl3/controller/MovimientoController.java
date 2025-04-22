package com.store.cont4bl3.controller;

import com.store.cont4bl3.dto.MovimientoDto;
import com.store.cont4bl3.security.JwtUtils;
import com.store.cont4bl3.service.MovimientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Obtiene todos los movimientos contables registrados en el sistema.
     *
     * @return Lista de movimientos
     */
    @GetMapping
    @Operation(summary = "Listar todos los movimientos contables",
            description = "Devuelve una lista de todos los movimientos registrados en el sistema contable.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimientos encontrados correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public List<MovimientoDto> getAll() {
        return movimientoService.findAll();
    }

    /**
     * Crea un nuevo movimiento contable a partir del cuerpo de la solicitud.
     * El usuario se extrae automáticamente desde la cookie JWT.
     *
     * @param movimientoDto Datos del movimiento a crear
     * @param request       HttpServletRequest para acceder a las cookies
     * @return Movimiento creado
     */
    @PostMapping
    @Operation(summary = "Crear un nuevo movimiento contable",
            description = "Crea un nuevo movimiento contable. El usuario se extrae automáticamente desde la cookie JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movimiento creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public MovimientoDto create(
            @Valid @RequestBody MovimientoDto movimientoDto,
            @Parameter(hidden = true) HttpServletRequest request) {

        String usuario = obtenerUsuarioDesdeJwt(request);
        movimientoDto.setUsuario(usuario);

        return movimientoService.save(movimientoDto);
    }

    /**
     * Extrae el nombre de usuario desde la cookie JWT en la solicitud HTTP.
     *
     * @param request HttpServletRequest
     * @return Nombre de usuario extraído del JWT
     */
    private String obtenerUsuarioDesdeJwt(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    return jwtUtils.extractUsername(cookie.getValue());
                }
            }
        }
        throw new RuntimeException("No se pudo obtener el usuario desde el JWT");
    }
        @GetMapping("/debug-token")
        public ResponseEntity<String> debugToken(@RequestHeader("Authorization") String authorizationHeader) {
            System.out.println(" JWT recibido: " + authorizationHeader);
            return ResponseEntity.ok("Token recibido");
        }
    }


