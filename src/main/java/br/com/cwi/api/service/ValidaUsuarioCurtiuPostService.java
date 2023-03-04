package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.repository.CurtidaRepository;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.Boolean.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaUsuarioCurtiuPostService {

    final String ERRO_ACAO_NAO_PERMITIDA = "Você não pode realizar essa ação";
    @Autowired
    private CurtidaRepository curtidaRepository;

    public void validar(Usuario usuario, Post post, boolean resultadoEsperado) {

        if(compare(resultadoEsperado, curtidaRepository.existsByUsuarioAndPost(usuario, post)) != 0){
            throw new ResponseStatusException(BAD_REQUEST, ERRO_ACAO_NAO_PERMITIDA);
        }
    }
}
