package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.controller.response.IdResponse;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.mapper.IdResponseMapper;
import br.com.cwi.api.mapper.IncluirPostMapper;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static br.com.cwi.api.mapper.IdResponseMapper.*;
import static br.com.cwi.api.mapper.IncluirPostMapper.*;

@Service
public class IncluirPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Transactional
    public IdResponse incluir(IncluirPostRequest request) {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        Post post = toEntity(request);
        post.setUsuario(usuarioAutenticado);
        post.setDataPostagem(LocalDate.now());

        postRepository.save(post);

        return toResponse(post);
    }
}
