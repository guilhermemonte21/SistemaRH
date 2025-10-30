package com.guilherme.AppRH.Service;

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

    public ColaboradorService(ColaboradorRepository colaboradorRepository, DepartamentoRepository departamentoRepository) {
        this.colaboradorRepository = colaboradorRepository;
        this.departamentoRepository = departamentoRepository;
    }

    public Colaborador Cadastrar(Colaborador colaborador){
        return colaboradorRepository.save(colaborador);
    }

    public Colaborador BuscarPorId(UUID id){
        return colaboradorRepository.findById(id).orElse(null);
    }
    public Colaborador Atualizar(ColaboradorDTO colaborador, Integer id){

        Colaborador col = new Colaborador();
        col.setColaboradorId(colaborador.getColaboradorId());
        col.setColaboradorNome(colaborador.getColaboradorNome());
        col.setColaboradorCPF(colaborador.getColaboradorCPF());
        col.setColaboradorDataNascimento(colaborador.getColaboradorDataNascimento());
        col.setColaboradorEmail(colaborador.getColaboradorEmail());
        col.setColaboradorTelefone(colaborador.getColaboradorTelefone());

            Departamento DepartamentoById = this.departamentoRepository.findById(id)
                .orElse(null);
        col.setDepartamento(DepartamentoById);


        return colaboradorRepository.save(col);
    }

    public void Deletar(UUID Id){
        colaboradorRepository.deleteById(Id);
    }
}
