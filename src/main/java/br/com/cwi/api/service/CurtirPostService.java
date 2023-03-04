package br.com.cwi.api.service;

import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.mapper.CurtirPostMapper;
import br.com.cwi.api.repository.CurtidaRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.mapper.CurtirPostMapper.*;

@Service
public class CurtirPostService {

    @Autowired
    private BuscarPostService buscarPostService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private ValidaUsuarioCurtiuPostService validaUsuarioCurtiuPostService;

    @Autowired
    private ValidaInteracaoPostService validaInteracaoPostService;

    @Transactional
    public void curtirPost(Long id) {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        Post post = buscarPostService.porId(id);
        validaInteracaoPostService.validar(post, usuarioAutenticado);
        validaUsuarioCurtiuPostService.validar(usuarioAutenticado, post, false);

        Curtida curtida = toEntity(post, usuarioAutenticado);

        curtidaRepository.save(curtida);
    }
}
