package com.guilherme.AppRH.security;

import com.guilherme.AppRH.Model.Entity.Usuario;
import com.guilherme.AppRH.Service.UsuarioService;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class securityService {
    private final UsuarioService usuarioService;

    public securityService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario UsuarioLogado(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) auth.getPrincipal();
        String login = user.getUsername();
        return usuarioService.GetByEmail(login);
    }
}
