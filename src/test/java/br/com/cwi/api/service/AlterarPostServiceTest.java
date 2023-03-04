package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlterarPostServiceTest {

    @InjectMocks
    private AlterarPostService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarPostService buscarPostService;

    @Mock
    private PostRepository postRepository;

    @Captor
    private ArgumentCaptor<Post> postCaptor;

    @Test
    @DisplayName("Deve alterar permissão do post")
    void deveAlterar(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        post.setSituacao(SituacaoPost.PUBLICO);

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPostService.porIdUsuario(post.getId(), usuario)).thenReturn(post);

        tested.alterarPermissao(post.getId());

        verify(postRepository).save(postCaptor.capture());

        assertEquals(SituacaoPost.PRIVADO, postCaptor.getValue().getSituacao());
    }

    @Test
    @DisplayName("Nao deve alterar permissão do post se o post nao existir")
    void naoDeveAlterar(){

        Long id = SimpleFactory.getRandomLong();
        Usuario usuario = UsuarioFactory.get();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        doThrow(ResponseStatusException.class).when(buscarPostService).porIdUsuario(id, usuario);

        assertThrows(ResponseStatusException.class, () -> {
            tested.alterarPermissao(id);
        });

        verifyNoInteractions(postRepository);
    }
}
