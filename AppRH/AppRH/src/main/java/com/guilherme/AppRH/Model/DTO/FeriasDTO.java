package com.guilherme.AppRH.Model.DTO;

import com.guilherme.AppRH.Model.Enum.StatusFerias;

import java.time.LocalDate;
import java.util.UUID;

public class FeriasDTO {
    private Integer FeriasID;
    private UUID ColaboradorId;
    private LocalDate FeriasDataInicio;
    private LocalDate FeriasDatafim;
    private StatusFerias FeriasStatus;

    public Integer getFeriasID() {
        return FeriasID;
    }

    public void setFeriasID(Integer feriasID) {
        FeriasID = feriasID;
    }

    public UUID getColaboradorId() {
        return ColaboradorId;
    }

    public void setColaboradorId(UUID colaboradorId) {
        ColaboradorId = colaboradorId;
    }

    public LocalDate getFeriasDataInicio() {
        return FeriasDataInicio;
    }

    public void setFeriasDataInicio(LocalDate feriasDataInicio) {
        FeriasDataInicio = feriasDataInicio;
    }

    public LocalDate getFeriasDatafim() {
        return FeriasDatafim;
    }

    public void setFeriasDatafim(LocalDate feriasDatafim) {
        FeriasDatafim = feriasDatafim;
    }

    public StatusFerias getFeriasStatus() {
        return FeriasStatus;
    }

    public void setFeriasStatus(StatusFerias feriasStatus) {
        FeriasStatus = feriasStatus;
    }
}
