package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Mappers.ColaboradorMapper;
import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import com.guilherme.AppRH.Repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ColaboradorService {
    private ColaboradorRepository colaboradorRepository;
    private DepartamentoRepository departamentoRepository;
    private ColaboradorMapper colaboradorMapper;

    public ColaboradorService(ColaboradorRepository colaboradorRepository, DepartamentoRepository departamentoRepository, ColaboradorMapper colaboradorMapper) {
        this.colaboradorRepository = colaboradorRepository;
        this.departamentoRepository = departamentoRepository;
        this.colaboradorMapper = colaboradorMapper;
    }

    public ColaboradorDTO Cadastrar(Colaborador colaborador){
        Colaborador NovoColaborador = colaboradorRepository.save(colaborador);
        ColaboradorDTO colaboradorDTO = colaboradorMapper.toDTO(NovoColaborador);
        return colaboradorDTO;
    }

    public ColaboradorDTO BuscarPorId(UUID id){
        Colaborador col = colaboradorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + id));
        ColaboradorDTO dto = colaboradorMapper.toDTO(col);
        return dto;
    }
    public void Atualizar(ColaboradorDTO colaborador, UUID id){

        Colaborador col = colaboradorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Colaborador não encontrado"));
        col.setColaboradorNome(colaborador.getColaboradorNome());
        col.setColaboradorEmail(colaborador.getColaboradorEmail());
        col.setColaboradorTelefone(colaborador.getColaboradorTelefone());

        Departamento DepartamentoById = this.departamentoRepository.findById(colaborador.getDepartamentoId())
                .orElse(null);
        col.setDepartamento(DepartamentoById);

        colaboradorRepository.save(col);
    }

    public void Deletar(UUID Id){
       colaboradorRepository.deleteById(Id);
    }
}
