package com.store.cont4bl3.controller;

import com.store.cont4bl3.model.MovimientoModel;
import com.store.cont4bl3.security.JwtUtils; // Asegúrate de importar esto
import com.store.cont4bl3.service.MovimientoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Se encontraron movimientos")
    @ApiResponse(responseCode = "404", description = "No se encontraron movimientos")
    @ApiResponse(responseCode = "500", description = "Error interno del server")
    public List<MovimientoModel> getAll(){
        return movimientoService.findAll();
    }

    @PostMapping
    @ApiResponse(responseCode = "200", description = "Se agregaron movimientos")
    @ApiResponse(responseCode = "404", description = "No se agregaron movimientos")
    @ApiResponse(responseCode = "500", description = "Error interno del server")
    public MovimientoModel create(@RequestBody MovimientoModel movimientoModel, HttpServletRequest request) {
        String usuario = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    usuario = jwtUtils.extractUsername(cookie.getValue());
                    break;
                }
            }
        }

        if (usuario == null) {
            throw new RuntimeException("No se pudo obtener el usuario desde el JWT");
        }

        movimientoModel.setUsuario(usuario);

        return movimientoService.save(movimientoModel);
    }
}
