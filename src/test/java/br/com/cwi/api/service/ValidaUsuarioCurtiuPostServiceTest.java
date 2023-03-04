package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidaUsuarioCurtiuPostServiceTest {

    @InjectMocks
    private ValidaUsuarioCurtiuPostService tested;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Test
    @DisplayName("Deve fazer nada se o resultado esperado é true")
    void deveFazerNadaSeResultadoEsperadoTrue(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();

        when(curtidaRepository.existsByUsuarioAndPost(usuario, post)).thenReturn(true);

        tested.validar(usuario, post, true);

        verify(curtidaRepository).existsByUsuarioAndPost(usuario, post);
    }

    @Test
    @DisplayName("Deve fazer nada se o resultado esperado é false")
    void deveFazerNadaSeResultadoEsperadoFalse(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();

        when(curtidaRepository.existsByUsuarioAndPost(usuario, post)).thenReturn(false);

        tested.validar(usuario, post, false);

        verify(curtidaRepository).existsByUsuarioAndPost(usuario, post);
    }

    @Test
    @DisplayName("Deve dar erro se o resultado esperado não é true")
    void deveDarErroSeResultadoEsperadoTrue(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();

        doThrow(ResponseStatusException.class)
                .when(curtidaRepository).existsByUsuarioAndPost(usuario, post);

        assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuario, post, false);
        });

        verify(curtidaRepository).existsByUsuarioAndPost(usuario, post);
    }

    @Test
    @DisplayName("Deve dar erro se o resultado esperado não é false")
    void deveDarErroSeResultadoEsperadoFalse(){

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();

        doThrow(ResponseStatusException.class)
                .when(curtidaRepository).existsByUsuarioAndPost(usuario, post);

        assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuario, post, true);
        });

        verify(curtidaRepository).existsByUsuarioAndPost(usuario, post);
    }
}
