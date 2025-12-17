package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.Entity.Usuario;
import com.guilherme.AppRH.Service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> Salvar(@RequestBody Usuario user){
        try {

            service.CadastrarUsuario(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{email}")
    public Usuario getByEmail(@PathVariable("email") String mail){
        return service.GetByEmail(mail);
    }
}
