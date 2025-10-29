package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }


    @PostMapping
    public Departamento cadastrar(@RequestBody Departamento departamento) {
        try {
            return service.CadastrarDepartamento(departamento.getDepartamentoNome());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public Departamento buscarPorId(@PathVariable Integer id) {
        try {
            return service.BuscarDepartamentoPorId(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        try {
            service.DeletarDepartamento(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}/colaboradores")
    public List<Colaborador> listarColaboradores(@PathVariable Integer id) {
        try {
            return service.ListarColaboradores(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
