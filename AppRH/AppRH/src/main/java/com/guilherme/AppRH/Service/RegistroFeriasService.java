package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Model.Entity.RegistroFerias;
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

    public RegistroFerias CadastrarFerias(RegistroFerias reg){
        return registroFeriasRepository.save(reg);
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
