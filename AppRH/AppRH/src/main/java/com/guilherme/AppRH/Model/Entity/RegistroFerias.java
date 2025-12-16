package com.guilherme.AppRH.Model.Entity;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Enum.StatusFerias;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "registroFerias")
@Getter
@Setter
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


}
