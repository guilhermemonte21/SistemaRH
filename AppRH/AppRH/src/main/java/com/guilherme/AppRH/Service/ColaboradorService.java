package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
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
    public Colaborador Atualizar(ColaboradorDTO colaborador){

        Colaborador col = new Colaborador();
        col.setColaboradorNome(colaborador.getColaboradorNome());
        col.setColaboradorCPF(colaborador.getColaboradorCPF());
        col.setColaboradorEmail(colaborador.getColaboradorEmail());
        col.setColaboradorTelefone(colaborador.getColaboradorTelefone());

        return colaboradorRepository.save(col);
    }

    public void Deletar(UUID Id){
        colaboradorRepository.deleteById(Id);
    }
}
