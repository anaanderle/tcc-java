package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.PostFactory;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListarMePostsServiceTest {

    @InjectMocks
    private ListarMePostsService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private PostRepository postRepository;

    @Test
    @DisplayName("Deve listar posts da home do usuario")
    void deveListarPosts(){

        Usuario usuario = UsuarioFactory.get();
        Pageable pageable = PageRequest.of(0, 5);
        List<Post> posts = List.of(
                PostFactory.get(),
                PostFactory.get(),
                PostFactory.get()
        );
        Page<Post> postsPaginado = new PageImpl<>(posts);

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(postRepository.getMePost(usuario, pageable)).thenReturn(postsPaginado);

        Page<PostResponse> response = tested.listar(pageable);

        verify(postRepository).getMePost(usuario, pageable);
        assertEquals(posts.size(), response.getSize());
    }

    @Test
    @DisplayName("Deve retornar lista vazia se nao tiver posts")
    void deveRetornarVazio(){

        Usuario usuario = UsuarioFactory.get();
        Pageable pageable = PageRequest.of(0, 5);
        List<Post> posts = new ArrayList<>();
        Page<Post> postsPaginado = new PageImpl<>(posts);

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(postRepository.getMePost(usuario, pageable)).thenReturn(postsPaginado);

        Page<PostResponse> response = tested.listar(pageable);

        verify(postRepository).getMePost(usuario, pageable);
        assertEquals(posts.size(), response.getSize());
    }
}
