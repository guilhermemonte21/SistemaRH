package com.guilherme.AppRH.Service;

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

@Service
public class RegistroFeriasService {

    @Autowired
    private RegistroFeriasRepository registroFeriasRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public RegistroFerias CadastrarFerias(FeriasDTO reg){
        if(reg.getFeriasDatafim().isAfter(reg.getFeriasDataInicio())){

            RegistroFerias ferias = new RegistroFerias();
            ferias.setFeriasId(reg.getFeriasID());
            ferias.setColaboradorId(colaboradorRepository.findById(reg.getColaboradorId()).orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + reg.getColaboradorId())));
            ferias.setFeriasDataFim(reg.getFeriasDatafim());
            ferias.setFeriasDataInicio(reg.getFeriasDataInicio());
            ferias.setFeriasDuracaoDias(reg.getFeriasDataInicio().datesUntil(reg.getFeriasDatafim()).count());
            ferias.setFeriasStatus(reg.getFeriasStatus());
            return registroFeriasRepository.save(ferias);


        }
        else {
           return null;
        }
    }

    public RegistroFerias BuscarFeriasById (Integer id){
        return registroFeriasRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + id));
    }

    public void DeletarRegistroFeriasById(Integer id){
        registroFeriasRepository.deleteById(id);
    }

    public List<RegistroFerias> ListarRegistros(){
        return registroFeriasRepository.findAll();
    }
}
