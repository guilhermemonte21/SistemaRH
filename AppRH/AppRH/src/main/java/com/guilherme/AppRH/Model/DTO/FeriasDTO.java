package com.guilherme.AppRH.Model.DTO;

import com.guilherme.AppRH.Model.Enum.StatusFerias;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class FeriasDTO {

    private Integer FeriasID;

    @NotNull
    private UUID ColaboradorId;
    @NotNull
    @FutureOrPresent
    private LocalDate FeriasDataInicio;
    @NotNull
    private LocalDate FeriasDatafim;

    private Long FeriasDuracao;
    @NotNull
    private StatusFerias FeriasStatus;


}
