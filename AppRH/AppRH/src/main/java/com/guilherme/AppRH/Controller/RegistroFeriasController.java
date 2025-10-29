package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.Entity.RegistroFerias;
import com.guilherme.AppRH.Service.RegistroFeriasService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/ferias")
public class RegistroFeriasController {

    private final RegistroFeriasService registroFeriasService;

    public RegistroFeriasController(RegistroFeriasService registroFeriasService) {
        this.registroFeriasService = registroFeriasService;
    }


    @PostMapping
    public RegistroFerias cadastrar(@RequestBody RegistroFerias reg) {
        try {
            return registroFeriasService.CadastrarFerias(reg);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public RegistroFerias buscarPorId(@PathVariable Integer id) {
        try {
            return registroFeriasService.BuscarFeriasById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        try {
            registroFeriasService.DeletarRegistroFeriasById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping
    public List<RegistroFerias> listarTodos() {
        try {
            return registroFeriasService.ListarRegistros();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
