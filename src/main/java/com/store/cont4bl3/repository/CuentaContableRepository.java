package com.store.cont4bl3.repository;

import com.store.cont4bl3.model.CuentaContableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaContableRepository extends JpaRepository<CuentaContableModel, Integer> {
}
