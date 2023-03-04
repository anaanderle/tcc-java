package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.com.cwi.api.domain.SituacaoAmizade.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidaInteracaoPostService {

    final String ERRO_NAO_PODE_INTERAGIR = "Você não pode interagir com esse post";

    @Autowired
    private AmizadeRespository amizadeRespository;

    public void validar(Post post, Usuario usuario) {

        if(post.getSituacao().equals(SituacaoPost.PRIVADO)){
            if(!amizadeRespository.existsByUsuarioAndAmigoAndSituacao(post.getUsuario(), usuario, ATIVA) &&
                    !amizadeRespository.existsByUsuarioAndAmigoAndSituacao(usuario, post.getUsuario(), ATIVA)){
                throw new ResponseStatusException(BAD_REQUEST, ERRO_NAO_PODE_INTERAGIR);
            }

        }
    }
}
