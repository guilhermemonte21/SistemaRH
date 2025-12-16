package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import com.guilherme.AppRH.Service.ColaboradorService;
import com.guilherme.AppRH.security.securityService;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@RestController
@RequestMapping("/colaboradores")
@Slf4j

public class ColaboradorController {

    private final ColaboradorRepository repository;
    private final ColaboradorService service;

    public ColaboradorController(ColaboradorRepository repository, ColaboradorService service) {
        this.repository = repository;
        this.service = service;
    }

    //funciona
    @PostMapping("/post")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<ColaboradorDTO> salvar(@RequestBody ColaboradorDTO col) {
        try {
            log.info("Cadastrando Colaborador {}",col.getColaboradorNome());
            if(col.getDataNascimento().datesUntil(LocalDate.now()).count() >= 6575){
            ColaboradorDTO novoColaborador = this.service.Cadastrar(col);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoColaborador);
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
    public ResponseEntity<ColaboradorDTO> buscarPorId(@PathVariable("id") UUID id) {
        try {
           ColaboradorDTO colaborador = service.BuscarPorId(id);
           return ResponseEntity.ok(colaborador);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //funciona
    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Void> atualizar(@RequestBody @Valid ColaboradorDTO colaborador, @PathVariable UUID id) {

        try {
           service.Atualizar(colaborador, id);
           return ResponseEntity.status(HttpStatus.OK).build();

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    //funciona
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Void> deletar(@PathVariable("id") UUID Id) {
        log.info("Deletando autor de ID: {} ", Id);
            Optional<Colaborador> colaborador = repository.findById(Id);
            if(colaborador.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            else {
                this.service.Deletar(Id);
                return ResponseEntity.noContent().build();
            }
    }
}

