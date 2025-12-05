package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.DTO.ColaboradorDtoResponse;
import com.guilherme.AppRH.Model.DTO.DepartamentoDto;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }


    public Departamento CadastrarDepartamento(String nome){
        Departamento novoDepartamento = new Departamento();
        novoDepartamento.setDepartamentoNome(nome);

        return departamentoRepository.save(novoDepartamento);
    }

    public DepartamentoDto BuscarDepartamentoPorId(Integer id){

        Departamento dpto =  departamentoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + id));

        DepartamentoDto dp = new DepartamentoDto();
        dp.setId(dpto.getDepartamentoId());
        dp.setNome(dpto.getDepartamentoNome());
        return dp;

    }

    public void DeletarDepartamento(Integer id){

        departamentoRepository.deleteById(id);

    }

    public List<ColaboradorDtoResponse> ListarColaboradores(Integer  ID){
       Departamento dpto =  departamentoRepository.findById(ID)
               .orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + ID));
       return dpto.getColaboradorList().stream().map(c ->
       {
           ColaboradorDtoResponse dto = new ColaboradorDtoResponse();
           dto.setColaboradorId(c.getColaboradorId());
           dto.setColaboradorNome(c.getColaboradorNome());
           dto.setColaboradorEmail(c.getColaboradorEmail());
           dto.setColaboradorTelefone(c.getColaboradorTelefone());
           return dto;
       }).collect(Collectors.toList());


    }
}
