package com.guilherme.AppRH.Mappers;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

@Component
public class ColaboradorMapper {
    public static ColaboradorDTO toDTO(Colaborador colaborador){
        ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
        colaboradorDTO.setColaboradorId(colaborador.getColaboradorId());
        colaboradorDTO.setColaboradorNome(colaborador.getColaboradorNome());
        colaboradorDTO.setColaboradorCpf(colaborador.getColaboradorCPF());
        colaboradorDTO.setColaboradorEmail(colaborador.getColaboradorEmail());
        colaboradorDTO.setColaboradorTelefone(colaborador.getColaboradorTelefone());
        return colaboradorDTO;
    }
    public static Colaborador toEntity(ColaboradorDTO colaboradorDTO){
        Colaborador colaborador = new Colaborador();
        colaborador.setColaboradorId(colaboradorDTO.getColaboradorId());
        colaborador.setColaboradorNome(colaboradorDTO.getColaboradorNome());
        colaborador.setColaboradorCPF(colaboradorDTO.getColaboradorCpf());
        colaborador.setColaboradorEmail(colaboradorDTO.getColaboradorEmail());
        colaborador.setColaboradorTelefone(colaboradorDTO.getColaboradorTelefone());
        return colaborador;
    }
}
