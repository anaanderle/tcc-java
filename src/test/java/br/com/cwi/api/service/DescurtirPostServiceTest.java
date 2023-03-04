package br.com.cwi.api.service;

import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.CurtidaFactory;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.CurtidaRepository;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class DescurtirPostServiceTest {

    @InjectMocks
    private DescurtirPostService tested;

    @Mock
    private BuscarPostService buscarPostService;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Mock
    private ValidaUsuarioCurtiuPostService validaUsuarioCurtiuPostService;

    @Mock
    private BuscarCurtidaService buscarCurtidaService;

    @Mock
    private ValidaInteracaoPostService validaInteracaoPostService;

    @Test
    @DisplayName("Deve descurtir post com sucesso")
    void deveDesurtirPost(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        Curtida curtida = CurtidaFactory.get();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPostService.porId(post.getId())).thenReturn(post);
        when(buscarCurtidaService.porIdUsuario(post, usuario)).thenReturn(curtida);

        tested.descurtirPost(post.getId());

        verify(curtidaRepository).deleteById(curtida.getId());
        verify(validaInteracaoPostService).validar(post, usuario);
        verify(validaUsuarioCurtiuPostService).validar(usuario, post, true);
    }

    @Test
    @DisplayName("Nao deve descurtir post inexistente")
    void naoDeveDescurtirPostInexistente(){

        Usuario usuario = UsuarioFactory.get();
        Long id = SimpleFactory.getRandomLong();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        doThrow(ResponseStatusException.class)
                .when(buscarPostService).porId(id);

        assertThrows(ResponseStatusException.class, () -> {
            tested.descurtirPost(id);
        });

        verifyNoInteractions(validaInteracaoPostService, validaUsuarioCurtiuPostService, buscarCurtidaService, curtidaRepository);
    }

    @Test
    @DisplayName("Nao deve descurtir post sem permissao")
    void naoDeveDescurtirPostSemPermissao(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPostService.porId(post.getId())).thenReturn(post);

        doThrow(ResponseStatusException.class)
                .when(validaInteracaoPostService).validar(post, usuario);

        assertThrows(ResponseStatusException.class, () -> {
            tested.descurtirPost(post.getId());
        });

        verifyNoInteractions(validaUsuarioCurtiuPostService, buscarCurtidaService, curtidaRepository);
    }

    @Test
    @DisplayName("Nao deve descurtir post não curtido")
    void naoDeveCurtirPostJaCurtido1(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPostService.porId(post.getId())).thenReturn(post);

        doThrow(ResponseStatusException.class)
                .when(validaUsuarioCurtiuPostService).validar(usuario, post, true);

        assertThrows(ResponseStatusException.class, () -> {
            tested.descurtirPost(post.getId());
        });

        verify(validaInteracaoPostService).validar(post, usuario);
        verifyNoInteractions(buscarCurtidaService, curtidaRepository);
    }

    @Test
    @DisplayName("Nao deve descurtir post não curtido")
    void naoDeveDescurtirPostNaoCurtido2(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPostService.porId(post.getId())).thenReturn(post);

        doThrow(ResponseStatusException.class)
                .when(buscarCurtidaService).porIdUsuario(post, usuario);

        assertThrows(ResponseStatusException.class, () -> {
            tested.descurtirPost(post.getId());
        });

        verify(validaUsuarioCurtiuPostService).validar(usuario, post, true);
        verifyNoInteractions(curtidaRepository);
    }
}
