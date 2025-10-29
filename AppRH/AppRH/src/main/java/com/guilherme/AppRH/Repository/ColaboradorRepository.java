package com.guilherme.AppRH.Repository;

import com.guilherme.AppRH.Model.Entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ColaboradorRepository extends JpaRepository<Colaborador, UUID> {
}
