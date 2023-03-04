package br.com.cwi.api.service;

import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.repository.CurtidaRepository;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BuscarCurtidaService {

    final String ERRO_POST_NAO_CURTIDO = "Este post não foi curtido";
    @Autowired
    private CurtidaRepository curtidaRepository;

    public Curtida porIdUsuario(Post post, Usuario usuario) {

        return curtidaRepository.findByUsuarioAndPost(usuario, post)
                .orElseThrow(
                        () -> new ResponseStatusException(BAD_REQUEST, ERRO_POST_NAO_CURTIDO)
                );
    }
}
