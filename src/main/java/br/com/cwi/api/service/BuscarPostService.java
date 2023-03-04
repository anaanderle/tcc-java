package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BuscarPostService {

    final String ERRO_POST_INEXISTENTE = "Post inexistente";
    final String ERRO_POST_INEXISTENTE_OU_SEM_PERMISSOES = "Post inexistente ou você não tem permissões";

    @Autowired
    private PostRepository postRepository;

    public Post porIdUsuario(Long id, Usuario usuario) {

        return postRepository.findByIdAndUsuario(id, usuario)
                .orElseThrow(
                        () -> new ResponseStatusException(BAD_REQUEST, ERRO_POST_INEXISTENTE)
                );
    }

    public Post porId(Long id) {

        return postRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(BAD_REQUEST, ERRO_POST_INEXISTENTE_OU_SEM_PERMISSOES)
                );
    }
}
