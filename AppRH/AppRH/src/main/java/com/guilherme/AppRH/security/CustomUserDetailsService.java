package com.guilherme.AppRH.security;

import com.guilherme.AppRH.Model.Entity.Usuario;
import com.guilherme.AppRH.Service.UsuarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioService service;

    public CustomUserDetailsService(UsuarioService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = service.GetByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Usuario não encontrado");
        }
        return User.builder()
                .username(user.getEmail())
                .password(user.getSenha())
                .roles(user.getRole().toString())
                .build();
    }


}
