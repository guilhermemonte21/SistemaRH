package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Mappers.ColaboradorMapper;
import com.guilherme.AppRH.Mappers.FeriasMapper;
import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.DTO.FeriasDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.RegistroFerias;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import com.guilherme.AppRH.Repository.RegistroFeriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegistroFeriasService {


    private RegistroFeriasRepository registroFeriasRepository;

    private  FeriasMapper feriasMapper;


    public RegistroFeriasService(FeriasMapper feriasMapper, RegistroFeriasRepository registroFeriasRepository ) {
        this.feriasMapper = feriasMapper;
        this.registroFeriasRepository = registroFeriasRepository;
    }

    public FeriasDTO CadastrarFerias(FeriasDTO reg){
        if(reg.getFeriasDatafim().isAfter(reg.getFeriasDataInicio())){

            RegistroFerias salvo = registroFeriasRepository.save(feriasMapper.toEntity(reg));

            FeriasDTO dto = feriasMapper.toDTO(salvo);
            return dto;

        }
        else {
           return null;
        }
    }

    public FeriasDTO BuscarFeriasById (Integer id){
         RegistroFerias reg = registroFeriasRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + id));
            FeriasDTO feriasDTO = feriasMapper.toDTO(reg);
            return feriasDTO;
    }

    public void DeletarRegistroFeriasById(Integer id){
        RegistroFerias registroFerias = registroFeriasRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Não existe Registro com o seguinte id:" + id));
        registroFeriasRepository.delete(registroFerias);
    }

    public List<FeriasDTO> ListarRegistros(){
        List<RegistroFerias> listaDeRegistrosFerias = registroFeriasRepository.findAll();
        return listaDeRegistrosFerias.stream().map(c ->
        {
            FeriasDTO feriasDTO = feriasMapper.toDTO(c);
            return feriasDTO;
        }).collect(Collectors.toList());

    }


}
