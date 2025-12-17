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


    private final DepartamentoRepository departamentoRepository;
    private final ColaboradorMapper colaboradorMapper;
    private final DepartamentoMapper departamentoMapper;

    public DepartamentoService(DepartamentoRepository departamentoRepository, ColaboradorMapper colaboradorMapper, DepartamentoMapper dp) {
        this.departamentoRepository = departamentoRepository;
        this.colaboradorMapper = colaboradorMapper;
        this.departamentoMapper = dp;
    }

    public DepartamentoDto CadastrarDepartamento(DepartamentoDto departamentoDto){
        Departamento novoDepartamento = departamentoMapper.toEntity(departamentoDto);

        Departamento DepartamentoCadastrado = departamentoRepository.save(novoDepartamento);

        return departamentoMapper.toDto(DepartamentoCadastrado);
    }

    public DepartamentoDto BuscarDepartamentoPorId(Integer id){

        Departamento dpto =  departamentoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + id));

        DepartamentoDto dp = departamentoMapper.toDto(dpto);
        return dp;

    }

    public void DeletarDepartamento(Integer id){
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
