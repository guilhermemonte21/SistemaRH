package com.guilherme.AppRH.Model.DTO;

import com.guilherme.AppRH.Model.Entity.Departamento;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;
@Getter
@Setter
public class ColaboradorDTO {

    private UUID colaboradorId;

    @NotBlank(message = "Nome é obrigatório")
    private String colaboradorNome;

    @CPF
    @NotBlank(message = "CPF é obrigatorio")
    private String colaboradorCpf;

    @Email
    @NotBlank(message = "Email Obrigatório")
    private String colaboradorEmail;

    @NotNull
    @Digits(integer = 11, fraction = 0)
    private Long colaboradorTelefone;

    @NotNull
    private LocalDate DataNascimento;

    @NotNull
    private Integer departamentoId;


}
