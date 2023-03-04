package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.mapper.PostMapper;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class ListarPostsService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private BuscarAmizadeService buscarAmizadeService;

    @Autowired
    private PostRepository postRepository;

    public List<PostResponse> listar(Long id) {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        Usuario usuario = buscarUsuarioService.porId(id);
        List<Post> posts;

        if(buscarAmizadeService.validar(usuarioAutenticado, usuario)){
            posts = postRepository.findByUsuario(usuario);

            return posts.stream()
                    .map(PostMapper::toResponse)
                    .collect(toList());
        }

        posts = postRepository.findByUsuarioAndSituacao(usuario, SituacaoPost.PUBLICO);

        return posts.stream()
                .map(PostMapper::toResponse)
                .collect(toList());
    }
}