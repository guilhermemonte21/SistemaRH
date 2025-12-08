package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.DTO.FeriasDTO;
import com.guilherme.AppRH.Model.Entity.RegistroFerias;
import com.guilherme.AppRH.Service.RegistroFeriasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<FeriasDTO> cadastrar(@RequestBody FeriasDTO reg) {
        try {
            FeriasDTO RegistroFerias = this.registroFeriasService.CadastrarFerias(reg);
            return ResponseEntity.status(HttpStatus.CREATED).body(RegistroFerias);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<FeriasDTO> buscarPorId(@PathVariable Integer id) {
        try {
            FeriasDTO FeriasById = this.registroFeriasService.BuscarFeriasById(id);
            return ResponseEntity.ok().body(FeriasById);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            this.registroFeriasService.DeletarRegistroFeriasById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping
    public List<FeriasDTO> ListaDeRegistros(){
        return this.registroFeriasService.ListarRegistros();
    }

}
