package com.guilherme.AppRH.Model.DTO;

import java.util.UUID;

public class ColaboradorDtoResponse {
    private UUID colaboradorId;
    private String colaboradorNome;
    private String colaboradorEmail;
    private Long colaboradorTelefone;

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
}
