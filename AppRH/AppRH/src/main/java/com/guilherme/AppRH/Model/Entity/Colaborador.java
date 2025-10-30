package com.guilherme.AppRH.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "colaborador")
public class Colaborador {
    @Id
    @Column(name = "ColaboradorId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ColaboradorId;

    @Column(name = "ColaboradorNome")
    private String ColaboradorNome;

    @Column(name = "ColaboradorCpf")
    private String ColaboradorCPF;

    @Column(name = "ColaboradorDataNascimento")
    private LocalDate ColaboradorDataNascimento;

    @Column(name = "ColaboradorEmail")
    private String ColaboradorEmail;

    @Column(name = "ColaboradorTelefone")
    private Long ColaboradorTelefone;

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

    public Long getColaboradorTelefone() {
        return ColaboradorTelefone;
    }

    public void setColaboradorTelefone(Long colaboradorTelefone) {
        ColaboradorTelefone = colaboradorTelefone;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
