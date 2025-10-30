package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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

    public Departamento BuscarDepartamentoPorId(Integer id){

        return departamentoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + id));

    }

    public void DeletarDepartamento(Integer id){

        departamentoRepository.deleteById(id);

    }

    public List<Colaborador> ListarColaboradores(Integer  ID){
       Departamento dpto =  departamentoRepository.findById(ID)
               .orElseThrow(() -> new NoSuchElementException("Departamento não encontrado com o ID: " + ID));

        return dpto.getColaboradorList();
    }
}
