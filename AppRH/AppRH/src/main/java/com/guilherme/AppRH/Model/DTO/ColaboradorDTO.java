package com.guilherme.AppRH.Model.DTO;

import java.time.LocalDate;
import java.util.UUID;

public class ColaboradorDTO {
    private UUID colaboradorId;
    private String colaboradorNome;
    private String colaboradorCPF;
    private LocalDate colaboradorDataNascimento;
    private String colaboradorEmail;
    private Long colaboradorTelefone;
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

    public String getColaboradorCPF() {
        return colaboradorCPF;
    }

    public void setColaboradorCPF(String colaboradorCPF) {
        this.colaboradorCPF = colaboradorCPF;
    }

    public LocalDate getColaboradorDataNascimento() {
        return colaboradorDataNascimento;
    }

    public void setColaboradorDataNascimento(LocalDate colaboradorDataNascimento) {
        this.colaboradorDataNascimento = colaboradorDataNascimento;
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
}
