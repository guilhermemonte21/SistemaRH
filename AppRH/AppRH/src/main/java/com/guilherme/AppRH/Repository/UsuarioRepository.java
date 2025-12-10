package com.guilherme.AppRH.Repository;

import com.guilherme.AppRH.Model.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
