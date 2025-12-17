package com.guilherme.AppRH.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoDto {

    private Integer Id;

    @NotBlank(message = "Nome do Departamento é Obrigatorio")
    private String Nome;

}
