package com.store.cont4bl3.service;

import com.store.cont4bl3.dto.CuentaContableDto;
import com.store.cont4bl3.model.CuentaContableModel;
import com.store.cont4bl3.repository.CuentaContableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;



import java.util.Optional;

@Service
public class CuentaContableService {


    @Autowired
    CuentaContableRepository cuentaContableRepository;
    //Buscar Todas las cuentas contables
    public Page<CuentaContableDto> findAll(Pageable pageable) {
        return cuentaContableRepository.findAll(pageable)
                .map(this::toDto);
    }
    //Buscar por Id
    public Optional<CuentaContableModel> findById(Integer id) {
        return cuentaContableRepository.findById(id);
    }
    //Guardar registros
    public CuentaContableDto save(CuentaContableDto dto) {
        CuentaContableModel model = toEntity(dto);
        CuentaContableModel saved = cuentaContableRepository.save(model);
        return toDto(saved);
    }




    public CuentaContableDto toDto(CuentaContableModel cuentaContableModel) {
        CuentaContableDto dto = new CuentaContableDto();
        dto.setIdCuenta(cuentaContableModel.getIdCuenta());
        dto.setNombreCuenta(cuentaContableModel.getNombreCuenta());
        dto.setTipoCuenta(cuentaContableModel.getTipoCuenta());
        dto.setSaldoInicial(cuentaContableModel.getSaldoInicial());
        return dto;
    }

    // Mapeo DTO → entidad
    private CuentaContableModel toEntity(CuentaContableDto dto) {
        CuentaContableModel model = new CuentaContableModel();
        model.setIdCuenta(dto.getIdCuenta()); // puede ser null y lo generará la BD
        model.setNombreCuenta(dto.getNombreCuenta());
        model.setTipoCuenta(dto.getTipoCuenta());
        model.setSaldoInicial(dto.getSaldoInicial());
        return model;
    }

}
