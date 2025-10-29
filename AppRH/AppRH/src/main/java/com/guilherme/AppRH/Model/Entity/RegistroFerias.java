package com.guilherme.AppRH.Model.Entity;

import com.guilherme.AppRH.Model.Enum.StatusFerias;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class RegistroFerias {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer FeriasId;

    @OneToOne
    private Colaborador Colaborador;

    private LocalDate FeriasDataInicio;

    private LocalDate FeriasDataFim;

    private int FeriasDuracaoDias;

    @Enumerated(EnumType.STRING)
    private StatusFerias FeriasStatus;

    public Integer getFeriasId() {
        return FeriasId;
    }

    public void setFeriasId(Integer feriasId) {
        FeriasId = feriasId;
    }

    public Colaborador getColaboradorId() {
        return Colaborador;
    }

    public void setColaboradorId(Colaborador colaboradorId) {
        Colaborador = colaboradorId;
    }

    public LocalDate getFeriasDataInicio() {
        return FeriasDataInicio;
    }

    public void setFeriasDataInicio(LocalDate feriasDataInicio) {
        FeriasDataInicio = feriasDataInicio;
    }

    public LocalDate getFeriasDataFim() {
        return FeriasDataFim;
    }

    public void setFeriasDataFim(LocalDate feriasDataFim) {
        FeriasDataFim = feriasDataFim;
    }

    public int getFeriasDuracaoDias() {
        return FeriasDuracaoDias;
    }

    public void setFeriasDuracaoDias(int feriasDuracaoDias) {
        FeriasDuracaoDias = feriasDuracaoDias;
    }

    public StatusFerias getFeriasStatus() {
        return FeriasStatus;
    }

    public void setFeriasStatus(StatusFerias feriasStatus) {
        FeriasStatus = feriasStatus;
    }
}
