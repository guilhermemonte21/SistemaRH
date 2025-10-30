package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
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

    //funciona
    @PostMapping("/post")
    public Colaborador salvar(@RequestBody Colaborador col) {
        try {
            return this.service.Cadastrar(col);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //funciona
    @GetMapping("/{id}")
    public Colaborador buscarPorId(@PathVariable("id") UUID id) {
        try {
           Colaborador col = service.BuscarPorId(id);
           return col;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //funciona
    @PutMapping("/atualizar")
    public Colaborador atualizar(@RequestBody ColaboradorDTO colaborador) {
        try {
            return this.service.Atualizar(colaborador);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    //funciona
    @DeleteMapping("/delete/{id}")
    public void deletar(@PathVariable("id") UUID Id) {
        try {
            this.service.Deletar(Id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

