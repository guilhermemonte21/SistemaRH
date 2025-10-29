package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ColaboradorService {
    private ColaboradorRepository colaboradorRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public Colaborador Cadastrar(Colaborador colaborador){
        return colaboradorRepository.save(colaborador);
    }

    public Colaborador BuscarPorId(UUID id){
        return colaboradorRepository.findById(id).orElse(null);
    }
    public void Atualizar(Colaborador colaborador){
        colaboradorRepository.save(colaborador);
    }

    public void Deletar(Colaborador colaborador){
        colaboradorRepository.delete(colaborador);
    }
}
