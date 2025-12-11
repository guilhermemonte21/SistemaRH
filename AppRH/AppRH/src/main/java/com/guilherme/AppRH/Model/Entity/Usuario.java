package com.guilherme.AppRH.Model.Entity;

import com.guilherme.AppRH.Model.Enum.TipoUsuario;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "UsuarioId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID IdUsuario;

    @Column(name = "ColaboradorEmail")
    private String email;

    @Column(name = "ColaboradorSenha")
    private String Senha;

    @Column(name = "TipoUsuario")
    private TipoUsuario Role;

    public UUID getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        email = mail;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public TipoUsuario getRole() {
        return Role;
    }

    public void setRole(TipoUsuario role) {
        Role = role;
    }
}
