package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirComentarioPostRequest;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.ComentarioRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IncluirComentarioPostServiceTest {

    @InjectMocks
    private IncluirComentarioPostService tested;

    @Mock
    private BuscarPostService buscarPostService;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private ValidaInteracaoPostService validaInteracaoPostService;

    @Captor
    private ArgumentCaptor<Comentario> comentarioCaptor;

    @Test
    @DisplayName("Deve incluir comentário em post existente")
    void deveIncluirComentario(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        IncluirComentarioPostRequest request = new IncluirComentarioPostRequest();
        request.setDescricao("descricao");

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarPostService.porId(post.getId())).thenReturn(post);

        tested.incluirComentario(post.getId(), request);

        verify(comentarioRepository).save(comentarioCaptor.capture());

        assertEquals(request.getDescricao(), comentarioCaptor.getValue().getDescricao());
    }

    @Test
    @DisplayName("Nao deve incluir comentário em post inexistente")
    void naoDeveIncluirComentario(){

        Long id = SimpleFactory.getRandomLong();
        IncluirComentarioPostRequest request = new IncluirComentarioPostRequest();

        doThrow(ResponseStatusException.class)
                .when(buscarPostService).porId(id);

        assertThrows(ResponseStatusException.class, () -> {
            tested.incluirComentario(id, request);
        });

        verifyNoInteractions(validaInteracaoPostService, comentarioRepository);
    }
}
