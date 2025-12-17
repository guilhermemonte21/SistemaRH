package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.DTO.FeriasDTO;
import com.guilherme.AppRH.Model.Entity.RegistroFerias;
import com.guilherme.AppRH.Model.Enum.StatusFerias;
import com.guilherme.AppRH.Service.RegistroFeriasService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ferias")
@Tag(name = "Registro Férias")
@Slf4j
public class RegistroFeriasController {

    private final RegistroFeriasService registroFeriasService;

    public RegistroFeriasController(RegistroFeriasService registroFeriasService) {
        this.registroFeriasService = registroFeriasService;
    }


    @PostMapping
    public ResponseEntity<FeriasDTO> cadastrar(@RequestBody @Valid FeriasDTO reg) {
        try {
            if(reg.getFeriasDatafim().isAfter(reg.getFeriasDataInicio())){
            FeriasDTO RegistroFerias = this.registroFeriasService.CadastrarFerias(reg);
            return ResponseEntity.status(HttpStatus.CREATED).body(RegistroFerias);}
            else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data final é invalida");
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<FeriasDTO>> buscarPorId(@PathVariable Integer id) {

            Optional<FeriasDTO> FeriasById = this.registroFeriasService.BuscarFeriasById(id);
            if(FeriasById.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(FeriasById);
            }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        log.info("Deletando autor de ID: {} ", id);
            Optional<FeriasDTO> feriasDTO = registroFeriasService.BuscarFeriasById(id);
            if(feriasDTO.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            else{
                registroFeriasService.DeletarRegistroFeriasById(id);
                return ResponseEntity.noContent().build();
            }

    }
    @GetMapping
    public List<FeriasDTO> ListaDeRegistros(){
        return this.registroFeriasService.ListarRegistros();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> Atualizar(@PathVariable("id") Integer id, StatusFerias status){
        Optional<FeriasDTO> registroFerias = registroFeriasService.BuscarFeriasById(id);
        if(registroFerias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            registroFeriasService.atualizar(id, status);
            return ResponseEntity.ok().build();
        }
        }

    }


