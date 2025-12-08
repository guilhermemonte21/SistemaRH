package com.guilherme.AppRH.Mappers;

import com.guilherme.AppRH.Model.DTO.DepartamentoDto;
import com.guilherme.AppRH.Model.Entity.Departamento;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoMapper {
    public static DepartamentoDto toDto(Departamento departamento){
        DepartamentoDto dto = new DepartamentoDto();
        dto.setId(departamento.getDepartamentoId());
        dto.setNome(departamento.getDepartamentoNome());
        return dto;
    }
    public static Departamento toEntity(DepartamentoDto dto){
        Departamento departamento = new Departamento();
        departamento.setDepartamentoId(dto.getId());
        departamento.setDepartamentoNome(dto.getNome());
        return departamento;
    }
}
