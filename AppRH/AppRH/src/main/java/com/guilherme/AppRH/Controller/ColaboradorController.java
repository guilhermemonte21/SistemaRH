package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Mappers.ColaboradorMapper;
import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Repository.ColaboradorRepository;
import com.guilherme.AppRH.Service.ColaboradorService;
import com.guilherme.AppRH.security.securityService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Colaborador")
public class ColaboradorController {

    private final ColaboradorRepository repository;
    private final ColaboradorService service;
    private final ColaboradorMapper mapper;

    public ColaboradorController(ColaboradorRepository repository, ColaboradorService service, ColaboradorMapper mapper) {
        this.repository = repository;
        this.service = service;
        this.mapper = mapper;
    }


    @PostMapping("/post")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<ColaboradorDTO> salvar(@Valid @RequestBody ColaboradorDTO col) {
        try {
            log.info("Cadastrando Colaborador {}",col.getColaboradorNome());
            if(col.getDataNascimento().datesUntil(LocalDate.now()).count() >= 6575){
            ColaboradorDTO novoColaborador = this.service.Cadastrar(col);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoColaborador);
            }
            else {

                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<ColaboradorDTO>> buscarPorId(@PathVariable("id") UUID id) {

            Optional<ColaboradorDTO> ColaboradorBuscado = service.BuscarPorId(id);
           if(ColaboradorBuscado.isEmpty()){
               return ResponseEntity.notFound().build();
           }
           else{
               return ResponseEntity.ok(ColaboradorBuscado);
           }

    }


    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Void> atualizar(@RequestBody @Valid ColaboradorDTO colaborador, @PathVariable UUID id) {
            Optional<Colaborador> colaboradorOptional = service.BuscarPorId(id).map(mapper::toEntity);
        if(colaboradorOptional.isPresent()) {
            service.Atualizar(colaborador, id);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }



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

