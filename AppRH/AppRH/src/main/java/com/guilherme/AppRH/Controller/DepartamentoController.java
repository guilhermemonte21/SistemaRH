package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.DTO.ColaboradorDTO;
import com.guilherme.AppRH.Model.DTO.DepartamentoDto;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {


    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    //funciona
    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<DepartamentoDto> cadastrar(@RequestBody Departamento departamento) {
        try {
            DepartamentoDto dp = this.service.CadastrarDepartamento(departamento.getDepartamentoNome());
            return ResponseEntity.status(HttpStatus.CREATED).body(dp);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //funciona
    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDto> buscarPorId(@PathVariable("id") Integer id) {
        try {
            DepartamentoDto dto = this.service.BuscarDepartamentoPorId(id);
            return ResponseEntity.ok(dto);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //funciona
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            this.service.DeletarDepartamento(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    //funciona
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
