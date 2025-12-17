package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.DTO.DepartamentoDto;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Service.DepartamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
@Tag(name = "Departamento")
@Slf4j
public class DepartamentoController {


    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }


    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<DepartamentoDto> cadastrar(@RequestBody @Valid DepartamentoDto departamento) {
        try {
            log.info("Novo Departamento: {}", departamento);
            DepartamentoDto dp = this.service.CadastrarDepartamento(departamento);
            return ResponseEntity.status(HttpStatus.CREATED).body(dp);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<DepartamentoDto>> buscarPorId(@PathVariable("id") Integer id) {

            Optional<DepartamentoDto> dto = this.service.BuscarDepartamentoPorId(id);
            if(dto.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            else {
                return ResponseEntity.ok(dto);
            }


    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        log.info("Deletando o departamento com id: {}", id);
        Optional<DepartamentoDto> departamentoDto = service.BuscarDepartamentoPorId(id);
        if(departamentoDto.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            this.service.DeletarDepartamento(id);
            return ResponseEntity.noContent().build();
        }



    }


    @GetMapping("/{id}/colaboradores")
    public ResponseEntity<List<ColaboradorDTO>> listarColaboradores(@PathVariable("id") Integer id) {
        try {
            List<ColaboradorDTO> ListaColaboradores = this.service.ListarColaboradores(id);
            return ResponseEntity.ok(ListaColaboradores);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
