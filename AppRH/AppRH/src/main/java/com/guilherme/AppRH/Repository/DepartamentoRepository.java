package com.guilherme.AppRH.Repository;

import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

}
