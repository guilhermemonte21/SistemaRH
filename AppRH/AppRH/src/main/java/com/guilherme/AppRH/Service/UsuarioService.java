package com.guilherme.AppRH.Service;

import com.guilherme.AppRH.Model.Entity.Usuario;
import com.guilherme.AppRH.Repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    public void CadastrarUsuario(Usuario user){
        if (GetByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("E-mail já está cadastrado.");

        }
        String senha = user.getSenha();
        user.setSenha(encoder.encode(senha));
        usuarioRepository.save(user);
    }
    public Usuario GetByEmail(String Email){
        return usuarioRepository.findByEmail(Email);
    }
}
