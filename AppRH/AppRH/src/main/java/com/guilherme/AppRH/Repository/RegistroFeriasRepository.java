package com.guilherme.AppRH.Repository;

import com.guilherme.AppRH.Model.Entity.RegistroFerias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistroFeriasRepository extends JpaRepository<RegistroFerias, Integer> {
}
