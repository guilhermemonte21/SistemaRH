package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.DTO.ColaboradorDtoResponse;
import com.guilherme.AppRH.Model.DTO.DepartamentoDto;
import com.guilherme.AppRH.Model.Entity.Colaborador;
import com.guilherme.AppRH.Model.Entity.Departamento;
import com.guilherme.AppRH.Service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    //funciona
    @PostMapping
    public ResponseEntity<Departamento> cadastrar(@RequestBody Departamento departamento) {
        try {
            Departamento dp = this.service.CadastrarDepartamento(departamento.getDepartamentoNome());
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
            return ResponseEntity.status(HttpStatus.FOUND).body(dto);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //funciona
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            this.service.DeletarDepartamento(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //funciona
    @GetMapping("/{id}/colaboradores")
    public ResponseEntity<List<ColaboradorDtoResponse>> listarColaboradores(@PathVariable("id") Integer id) {
        try {
            List<ColaboradorDtoResponse> ListaColaboradores = this.service.ListarColaboradores(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(ListaColaboradores);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
