package com.guilherme.AppRH.Mappers;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import com.guilherme.AppRH.Repository.DepartamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.NoSuchElementException;

@Component
public class ColaboradorMapper {
    private DepartamentoRepository repository;

    public ColaboradorMapper(DepartamentoRepository repository) {
        this.repository = repository;
    }

    public ColaboradorDTO toDTO(Colaborador colaborador){
        ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
        colaboradorDTO.setColaboradorId(colaborador.getColaboradorId());
        colaboradorDTO.setColaboradorNome(colaborador.getColaboradorNome());
        colaboradorDTO.setColaboradorCpf(colaborador.getColaboradorCPF());
        colaboradorDTO.setColaboradorEmail(colaborador.getColaboradorEmail());
        colaboradorDTO.setColaboradorTelefone(colaborador.getColaboradorTelefone());
        colaboradorDTO.setDataNascimento(colaborador.getColaboradorDataNascimento());
        colaboradorDTO.setDepartamentoId(colaborador.getDepartamento().getDepartamentoId());
        return colaboradorDTO;
    }
    public Colaborador toEntity(ColaboradorDTO colaboradorDTO){
        Colaborador colaborador = new Colaborador();
        colaborador.setColaboradorId(colaboradorDTO.getColaboradorId());
        colaborador.setColaboradorNome(colaboradorDTO.getColaboradorNome());
        colaborador.setColaboradorCPF(colaboradorDTO.getColaboradorCpf());
        colaborador.setColaboradorEmail(colaboradorDTO.getColaboradorEmail());
        colaborador.setColaboradorTelefone(colaboradorDTO.getColaboradorTelefone());
        colaborador.setColaboradorDataNascimento(colaboradorDTO.getDataNascimento());
        colaborador.setDepartamento(repository.findById(colaboradorDTO.getDepartamentoId()).orElseThrow(() -> new NoSuchElementException("Departamento não existe" )));
        return colaborador;
    }
}
