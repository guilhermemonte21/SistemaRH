package com.guilherme.AppRH.Model.DTO;

import com.guilherme.AppRH.Model.Entity.Departamento;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

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
    private Integer departamentoId;

    public UUID getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(UUID colaboradorId) {
        this.colaboradorId = colaboradorId;
    }

    public String getColaboradorNome() {
        return colaboradorNome;
    }

    public void setColaboradorNome(String colaboradorNome) {
        this.colaboradorNome = colaboradorNome;
    }


    public String getColaboradorEmail() {
        return colaboradorEmail;
    }

    public void setColaboradorEmail(String colaboradorEmail) {
        this.colaboradorEmail = colaboradorEmail;
    }

    public Long getColaboradorTelefone() {
        return colaboradorTelefone;
    }

    public void setColaboradorTelefone(Long colaboradorTelefone) {
        this.colaboradorTelefone = colaboradorTelefone;
    }

    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getColaboradorCpf() {
        return colaboradorCpf;
    }

    public void setColaboradorCpf(String colaboradorCpf) {
        this.colaboradorCpf = colaboradorCpf;
    }
}
