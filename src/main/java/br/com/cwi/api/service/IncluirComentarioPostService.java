package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirComentarioPostRequest;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.mapper.IncluirComentarioPostMapper;
import br.com.cwi.api.repository.ComentarioRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.desktop.ScreenSleepEvent;

import static br.com.cwi.api.mapper.IncluirComentarioPostMapper.*;

@Service
public class IncluirComentarioPostService {

    @Autowired
    private BuscarPostService buscarPostService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ValidaInteracaoPostService validaInteracaoPostService;

    @Transactional
    public void incluirComentario(Long id, IncluirComentarioPostRequest request) {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        Post post = buscarPostService.porId(id);
        validaInteracaoPostService.validar(post, usuarioAutenticado);

        Comentario comentario = toEntity(request, post, usuarioAutenticado);

        comentarioRepository.save(comentario);
    }
}
