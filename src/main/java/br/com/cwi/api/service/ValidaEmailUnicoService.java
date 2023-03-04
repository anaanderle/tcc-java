package br.com.cwi.api.service;

import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaEmailUnicoService {

    final String ERRO_EMAIL_EXISTENTE = "Email já existente";

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(String email) {

        if(usuarioRepository.existsByEmail(email)){
            throw new ResponseStatusException(BAD_REQUEST, ERRO_EMAIL_EXISTENTE);
        }
    }
}
