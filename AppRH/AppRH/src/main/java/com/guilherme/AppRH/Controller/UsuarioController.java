package com.guilherme.AppRH.Controller;

import com.guilherme.AppRH.Model.Entity.Usuario;
import com.guilherme.AppRH.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public void Salvar(@RequestBody Usuario user){
        service.CadastrarUsuario(user);
    }

    @GetMapping("/{email}")
    public Usuario getByEmail(@PathVariable("email") String mail){
        return service.GetByEmail(mail);
    }
}
