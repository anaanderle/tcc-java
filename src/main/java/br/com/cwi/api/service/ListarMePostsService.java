package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.mapper.PostMapper;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarMePostsService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PostRepository postRepository;

    public Page<PostResponse> listar(Pageable pageable) {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();

        return postRepository.getMePost(usuarioAutenticado, pageable)
                .map(PostMapper::toResponse);
    }
}
