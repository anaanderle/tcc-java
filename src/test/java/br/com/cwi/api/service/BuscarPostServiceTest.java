package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static br.com.cwi.api.domain.SituacaoPost.PRIVADO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarPostServiceTest {

    @InjectMocks
    private BuscarPostService tested;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("Deve retornar post do usuario")
    void deveRetornarPostUsuario(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        when(postRepository.findByIdAndUsuario(post.getId(), usuario)).thenReturn(Optional.of(post));

        Post response = tested.porIdUsuario(post.getId(), usuario);

        assertEquals(post.getId(), response.getId());
    }

    @Test
    @DisplayName("Nao deve retornar post inexistente do usuario")
    void naoDeveRetornarPostUsuario(){

        Usuario usuario = UsuarioFactory.get();
        Long id = SimpleFactory.getRandomLong();
        doThrow(ResponseStatusException.class)
                .when(postRepository).findByIdAndUsuario(id, usuario);

        assertThrows(ResponseStatusException.class, () -> {
            tested.porIdUsuario(id, usuario);
        });

        verify(postRepository).findByIdAndUsuario(id, usuario);
    }

    @Test
    @DisplayName("Deve retornar post")
    void deveRetornarPost(){

        Post post = PostFactory.get();
        when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));

        Post response = tested.porId(post.getId());

        assertEquals(post.getId(), response.getId());
    }

    @Test
    @DisplayName("Nao deve retornar post inexistente")
    void naoDeveRetornarPostInexistente(){

        Long id = SimpleFactory.getRandomLong();
        doThrow(ResponseStatusException.class)
                .when(postRepository).findById(id);

        assertThrows(ResponseStatusException.class, () -> {
            tested.porId(id);
        });

        verify(postRepository).findById(id);
    }

    @Test
    @DisplayName("Nao deve retornar post sem permissoes")
    void naoDeveRetornarPostPermissao(){

        Post post = PostFactory.get();
        post.setSituacao(PRIVADO);
        doThrow(ResponseStatusException.class)
                .when(postRepository).findById(post.getId());

        assertThrows(ResponseStatusException.class, () -> {
            tested.porId(post.getId());
        });

        verify(postRepository).findById(post.getId());
    }
}
