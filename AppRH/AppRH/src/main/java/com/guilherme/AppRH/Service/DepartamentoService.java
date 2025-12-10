package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Mappers.ColaboradorMapper;
import com.guilherme.AppRH.Mappers.DepartamentoMapper;
import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.DTO.DepartamentoDto;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;
    private ColaboradorMapper colaboradorMapper;

    public DepartamentoService(DepartamentoRepository departamentoRepository, ColaboradorMapper colaboradorMapper) {
        this.departamentoRepository = departamentoRepository;
        this.colaboradorMapper = colaboradorMapper;
    }

    public Departamento CadastrarDepartamento(String nome){
        Departamento novoDepartamento = new Departamento();
        novoDepartamento.setDepartamentoNome(nome);

        return departamentoRepository.save(novoDepartamento);
    }

    public DepartamentoDto BuscarDepartamentoPorId(Integer id){

        Departamento dpto =  departamentoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + id));

        DepartamentoDto dp = DepartamentoMapper.toDto(dpto);
        return dp;

    }

    public void DeletarDepartamento(Integer id){
        Departamento departamento = departamentoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + id));
        departamentoRepository.deleteById(id);

    }

    public List<ColaboradorDTO> ListarColaboradores(Integer  ID){
       Departamento dpto =  departamentoRepository.findById(ID)
               .orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + ID));
       return dpto.getColaboradorList().stream().map(c ->
       {
           ColaboradorDTO dto = colaboradorMapper.toDTO(c);
           return dto;
       }).collect(Collectors.toList());


    }
}
