package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }


    @PostMapping("/post")
    public Colaborador salvar(@RequestBody Colaborador col) {
        try {
            return this.service.Cadastrar(col);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public Colaborador buscarPorId(@PathVariable UUID id) {
        try {
            return this.service.BuscarPorId(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @PutMapping("/atualizar")
    public Colaborador atualizar(@RequestBody Colaborador colaborador) {
        try {
            return this.service.Cadastrar(colaborador);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/delete")
    public void deletar(@RequestBody Colaborador col) {
        try {
            this.service.Deletar(col);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

