package com.store.cont4bl3.service;

import com.store.cont4bl3.model.CuentaContableModel;
import com.store.cont4bl3.repository.CuentaContableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaContableService {
    @Autowired
    CuentaContableRepository cuentaContableRepository;
    //Buscar Todas las cuentas contables
    public List<CuentaContableModel> findAll() {
        return cuentaContableRepository.findAll();
    }
    //Buscar por Id
    public Optional<CuentaContableModel> findById(Integer id) {
        return cuentaContableRepository.findById(id);
    }
    //Guardar registros
    public CuentaContableModel save(CuentaContableModel cuentaContableModel) {
        return cuentaContableRepository.save(cuentaContableModel);
    }
    //Eliminar por id (no creo usarlo)
    public void delete(Integer id) {
        cuentaContableRepository.deleteById(id);
    }
}
