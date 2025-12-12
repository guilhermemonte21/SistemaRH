package com.guilherme.AppRH.Model.Entity;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Enum.StatusFerias;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "registroFerias")
public class RegistroFerias {
    @Id
    @Column(name = "FeriasId")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer FeriasId;

    @ManyToOne
    @JoinColumn(name = "Colaborador")
    private Colaborador Colaborador;

    @Column(name = "FeriasDataInicio")
    private LocalDate FeriasDataInicio;

    @Column(name = "FeriasDataFim")
    private LocalDate FeriasDataFim;

    @Column(name = "FeriasDuracaoDias")
    private Long FeriasDuracaoDias;

    @Enumerated(EnumType.STRING)
    @Column(name = "FeriasStatus")
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

    public Long getFeriasDuracaoDias() {
        return FeriasDuracaoDias;
    }

    public void setFeriasDuracaoDias(Long feriasDuracaoDias) {
        FeriasDuracaoDias = feriasDuracaoDias;
    }

    public StatusFerias getFeriasStatus() {
        return FeriasStatus;
    }

    public void setFeriasStatus(StatusFerias feriasStatus) {
        FeriasStatus = feriasStatus;
    }
}
