package com.guilherme.AppRH.Mappers;

import com.guilherme.AppRH.Model.DTO.FeriasDTO;
import com.guilherme.AppRH.Model.Entity.RegistroFerias;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class FeriasMapper {

    private final ColaboradorRepository colaboradorRepository;

    public FeriasMapper(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public RegistroFerias toEntity(FeriasDTO reg){
        RegistroFerias ferias = new RegistroFerias();
        ferias.setFeriasId(reg.getFeriasID());
        ferias.setColaborador(colaboradorRepository.findById(reg.getColaboradorId()).orElseThrow(() -> new NoSuchElementException("Registro não encontrado com o ID: " + reg.getColaboradorId())));
        ferias.setFeriasDataFim(reg.getFeriasDatafim());
        ferias.setFeriasDataInicio(reg.getFeriasDataInicio());
        ferias.setFeriasDuracaoDias(reg.getFeriasDataInicio().datesUntil(reg.getFeriasDatafim()).count());
        ferias.setFeriasStatus(reg.getFeriasStatus());
        return ferias;
    }
    public FeriasDTO toDTO(RegistroFerias reg){
        FeriasDTO dto = new FeriasDTO();
        dto.setFeriasID(reg.getFeriasId());
        dto.setFeriasDataInicio(reg.getFeriasDataInicio());
        dto.setFeriasDatafim(reg.getFeriasDataFim());
        dto.setFeriasStatus(reg.getFeriasStatus());
        dto.setFeriasDuracao(reg.getFeriasDataInicio().datesUntil(reg.getFeriasDataFim()).count());
        dto.setColaboradorId(reg.getColaborador().getColaboradorId());
        return dto;
    }
}
