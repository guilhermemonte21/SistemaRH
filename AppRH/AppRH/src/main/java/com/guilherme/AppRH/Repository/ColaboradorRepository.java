package com.guilherme.AppRH.Repository;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColaboradorRepository extends JpaRepository<Colaborador, UUID> {
    @Query("SELECT c FROM Colaborador c WHERE MONTH(c.ColaboradorDataNascimento) = :mes")
    List<Colaborador> buscarPorMesAniversario(@Param("mes") int mes);

}
