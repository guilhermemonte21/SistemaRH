package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.DTO.ColaboradorDtoResponse;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
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
    public ResponseEntity<Colaborador> salvar(@RequestBody Colaborador col) {
        try {
            if(col.getColaboradorDataNascimento().datesUntil(LocalDate.now()).count() >= 6575){
            this.service.Cadastrar(col);
            return ResponseEntity.status(HttpStatus.CREATED).body(col);
            }
            else {

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //funciona
    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorDtoResponse> buscarPorId(@PathVariable("id") UUID id) {
        try {
           ColaboradorDtoResponse colaborador = service.BuscarPorId(id);
           return ResponseEntity.status(HttpStatus.FOUND).body(colaborador);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //funciona
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizar(@RequestBody ColaboradorDTO colaborador, @PathVariable UUID id) {

        try {
           service.Atualizar(colaborador, id);
           return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    //funciona
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID Id) {
        try {
            this.service.Deletar(Id );
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

