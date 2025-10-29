package com.guilherme.AppRH.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ColaboradorId;

    private String ColaboradorNome;

    private String ColaboradorCPF;

    private LocalDate ColaboradorDataNascimento;

    private String ColaboradorEmail;

    private Integer ColaboradorTelefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    public UUID getColaboradorId() {
        return ColaboradorId;
    }

    public void setColaboradorId(UUID colaboradorId) {
        ColaboradorId = colaboradorId;
    }

    public String getColaboradorNome() {
        return ColaboradorNome;
    }

    public void setColaboradorNome(String colaboradorNome) {
        ColaboradorNome = colaboradorNome;
    }

    public String getColaboradorCPF() {
        return ColaboradorCPF;
    }

    public void setColaboradorCPF(String colaboradorCPF) {
        ColaboradorCPF = colaboradorCPF;
    }

    public LocalDate getColaboradorDataNascimento() {
        return ColaboradorDataNascimento;
    }

    public void setColaboradorDataNascimento(LocalDate colaboradorDataNascimento) {
        ColaboradorDataNascimento = colaboradorDataNascimento;
    }

    public String getColaboradorEmail() {
        return ColaboradorEmail;
    }

    public void setColaboradorEmail(String colaboradorEmail) {
        ColaboradorEmail = colaboradorEmail;
    }

    public Integer getColaboradorTelefone() {
        return ColaboradorTelefone;
    }

    public void setColaboradorTelefone(Integer colaboradorTelefone) {
        ColaboradorTelefone = colaboradorTelefone;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
