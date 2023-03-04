package br.com.cwi.api.service;

import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.CurtidaRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class CurtirPostServiceTest {

    @InjectMocks
    private CurtirPostService tested;

    @Mock
    private BuscarPostService buscarPostService;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Mock
    private ValidaUsuarioCurtiuPostService validaUsuarioCurtiuPostService;

    @Mock
    private ValidaInteracaoPostService validaInteracaoPostService;

    @Captor
    private ArgumentCaptor<Curtida> curtidaCaptor;

    @Test
    @DisplayName("Deve curtir post com sucesso")
    void deveCurtirPost(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPostService.porId(post.getId())).thenReturn(post);

        tested.curtirPost(post.getId());

        verify(curtidaRepository).save(curtidaCaptor.capture());
        verify(validaInteracaoPostService).validar(post, usuario);
        verify(validaUsuarioCurtiuPostService).validar(usuario, post, false);
    }

    @Test
    @DisplayName("Nao deve curtir post inexistente")
    void naoDeveCurtirPostInexistente(){

        Usuario usuario = UsuarioFactory.get();
        Long id = SimpleFactory.getRandomLong();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        doThrow(ResponseStatusException.class)
                .when(buscarPostService).porId(id);

        assertThrows(ResponseStatusException.class, () -> {
            tested.curtirPost(id);
        });

        verifyNoInteractions(validaInteracaoPostService, validaUsuarioCurtiuPostService, curtidaRepository);
    }

    @Test
    @DisplayName("Nao deve curtir post sem permissao")
    void naoDeveCurtirPostSemPermissao(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPostService.porId(post.getId())).thenReturn(post);

        doThrow(ResponseStatusException.class)
                .when(validaInteracaoPostService).validar(post, usuario);

        assertThrows(ResponseStatusException.class, () -> {
            tested.curtirPost(post.getId());
        });

        verifyNoInteractions(validaUsuarioCurtiuPostService, curtidaRepository);
    }

    @Test
    @DisplayName("Nao deve curtir post já curtido")
    void naoDeveCurtirPostJaCurtido(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPostService.porId(post.getId())).thenReturn(post);

        doThrow(ResponseStatusException.class)
                .when(validaUsuarioCurtiuPostService).validar(usuario, post, false);

        assertThrows(ResponseStatusException.class, () -> {
            tested.curtirPost(post.getId());
        });

        verifyNoInteractions(curtidaRepository);
    }
}
