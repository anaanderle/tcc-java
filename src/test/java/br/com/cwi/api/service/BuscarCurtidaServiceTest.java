package br.com.cwi.api.service;

import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.CurtidaFactory;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.CurtidaRepository;
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

import static br.com.cwi.api.domain.SituacaoAmizade.SOLICITADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarCurtidaServiceTest {

    @InjectMocks
    private BuscarCurtidaService tested;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Test
    @DisplayName("Deve retornar curtida quando existir")
    void deveRetornarCurtida(){

        Post post = PostFactory.get();
        Usuario usuario = UsuarioFactory.get();
        Curtida curtida = CurtidaFactory.get();
        when(curtidaRepository.findByUsuarioAndPost(usuario, post)).thenReturn(Optional.of(curtida));

        Curtida response = tested.porIdUsuario(post, usuario);

        verify(curtidaRepository).findByUsuarioAndPost(usuario, post);
        assertEquals(curtida.getId(), response.getId());
    }

    @Test
    @DisplayName("Nao deve retornar curtida quando não existir")
    void naoDeveRetornarCurtida(){

        Post post = PostFactory.get();
        Usuario usuario = UsuarioFactory.get();
        doThrow(ResponseStatusException.class)
                .when(curtidaRepository).findByUsuarioAndPost(usuario, post);

        assertThrows(ResponseStatusException.class, () -> {
            tested.porIdUsuario(post, usuario);
        });

        verify(curtidaRepository).findByUsuarioAndPost(usuario, post);
    }
}
