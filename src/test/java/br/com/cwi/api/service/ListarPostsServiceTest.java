package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.PostResponse;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.api.domain.SituacaoPost.PUBLICO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListarPostsServiceTest {

    @InjectMocks
    private ListarPostsService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @Mock
    private BuscarAmizadeService buscarAmizadeService;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("Deve listar posts do usuario quando ele é amigo do outro usuario")
    void deveListarPostsAmigo(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        List<Post> posts = List.of(
                PostFactory.get(),
                PostFactory.get(),
                PostFactory.get()
        );

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(amigo.getId())).thenReturn(amigo);
        when(buscarAmizadeService.validar(usuario, amigo)).thenReturn(true);
        when(postRepository.findByUsuario(amigo)).thenReturn(posts);

        List<PostResponse> response = tested.listar(amigo.getId());

        assertEquals(posts.size(), response.size());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando é amigo do outro usuario e nao tem posts")
    void deveListarPostsAmigoVazio(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        List<Post> posts = new ArrayList<>();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(amigo.getId())).thenReturn(amigo);
        when(buscarAmizadeService.validar(usuario, amigo)).thenReturn(true);
        when(postRepository.findByUsuario(amigo)).thenReturn(posts);

        List<PostResponse> response = tested.listar(amigo.getId());

        assertEquals(posts.size(), response.size());
    }

    @Test
    @DisplayName("Deve listar posts do usuario quando ele não é amigo do outro usuario")
    void deveListarPostsNaoAmigo(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        List<Post> posts = List.of(
                PostFactory.get(),
                PostFactory.get(),
                PostFactory.get()
        );

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(amigo.getId())).thenReturn(amigo);
        when(buscarAmizadeService.validar(usuario, amigo)).thenReturn(false);
        when(postRepository.findByUsuarioAndSituacao(amigo, PUBLICO)).thenReturn(posts);

        List<PostResponse> response = tested.listar(amigo.getId());

        assertEquals(posts.size(), response.size());
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando não é amigo do outro usuario e nao tem posts")
    void deveListarPostsNaoAmigoVazio(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        List<Post> posts = new ArrayList<>();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(amigo.getId())).thenReturn(amigo);
        when(buscarAmizadeService.validar(usuario, amigo)).thenReturn(false);
        when(postRepository.findByUsuarioAndSituacao(amigo, PUBLICO)).thenReturn(posts);

        List<PostResponse> response = tested.listar(amigo.getId());

        assertEquals(posts.size(), response.size());
    }

    @Test
    @DisplayName("Deve dar erro quando o amigo não é encontrado")
    void deveDarErro(){

        Usuario usuario = UsuarioFactory.get();
        Long id = SimpleFactory.getRandomLong();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        doThrow(ResponseStatusException.class)
                .when(buscarUsuarioService).porId(id);

        assertThrows(ResponseStatusException.class, () -> {
            tested.listar(id);
        });

        verifyNoInteractions(buscarAmizadeService, postRepository);
    }
}
