package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.repository.PostRepository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IncluirPostServiceTest {

    @InjectMocks
    private IncluirPostService tested;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Captor
    private ArgumentCaptor<Post> postCaptor;

    @Test
    @DisplayName("Deve incluir post publico")
    void deveIncluirPostPublico(){

        IncluirPostRequest request = new IncluirPostRequest();
        request.setSituacao(SituacaoPost.PUBLICO);

        tested.incluir(request);

        verify(usuarioAutenticadoService).get();
        verify(postRepository).save(postCaptor.capture());

        assertEquals(SituacaoPost.PUBLICO, postCaptor.getValue().getSituacao());
    }

    @Test
    @DisplayName("Deve incluir post privado")
    void deveIncluirPostPrivado(){

        IncluirPostRequest request = new IncluirPostRequest();
        request.setSituacao(SituacaoPost.PRIVADO);

        tested.incluir(request);

        verify(usuarioAutenticadoService).get();
        verify(postRepository).save(postCaptor.capture());

        assertEquals(SituacaoPost.PRIVADO, postCaptor.getValue().getSituacao());
    }
}
