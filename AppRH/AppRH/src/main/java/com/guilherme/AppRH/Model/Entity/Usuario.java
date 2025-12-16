package com.guilherme.AppRH.Model.Entity;

import com.guilherme.AppRH.Model.Enum.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
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

}
