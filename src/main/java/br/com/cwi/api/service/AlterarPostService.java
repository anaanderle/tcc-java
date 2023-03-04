package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlterarPostService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarPostService buscarPostService;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public void alterarPermissao(Long id) {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        Post post = buscarPostService.porIdUsuario(id, usuarioAutenticado);

        if(post.getSituacao().equals(SituacaoPost.PRIVADO)){
            post.setSituacao(SituacaoPost.PUBLICO);
        }
        else{
            post.setSituacao(SituacaoPost.PRIVADO);
        }

        postRepository.save(post);
    }
}
