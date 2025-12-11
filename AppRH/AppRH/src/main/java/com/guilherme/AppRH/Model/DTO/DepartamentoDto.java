package com.guilherme.AppRH.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DepartamentoDto {

    @NotNull
    private Integer Id;

    @NotBlank(message = "Nome do Departamento é Obrigatorio")
    private String Nome;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
