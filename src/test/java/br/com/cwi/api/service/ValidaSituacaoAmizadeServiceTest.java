package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidaSituacaoAmizadeServiceTest {

    @InjectMocks
    private ValidaSituacaoAmizadeService tested;

    @Mock
    private AmizadeRespository amizadeRespository;

    @Test
    @DisplayName("Deve fazer nada se existir relaçao de amizade")
    void deveFazerNada(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        when(amizadeRespository.existsByUsuarioAndAmigo(usuario, amigo)).thenReturn(false);

        tested.validarSemAmizade(usuario, amigo);

        verify(amizadeRespository).existsByUsuarioAndAmigo(usuario, amigo);
    }

    @Test
    @DisplayName("Deve dar erro se existir relaçao de amizade")
    void deveDarErro(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();

        doThrow(ResponseStatusException.class)
                .when(amizadeRespository).existsByUsuarioAndAmigo(usuario, amigo);

        assertThrows(ResponseStatusException.class, () -> {
            tested.validarSemAmizade(usuario, amigo);
        });

        verify(amizadeRespository).existsByUsuarioAndAmigo(usuario, amigo);
    }
}
