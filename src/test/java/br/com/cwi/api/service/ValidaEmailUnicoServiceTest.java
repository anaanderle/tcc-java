package br.com.cwi.api.service;

import br.com.cwi.api.security.repository.UsuarioRepository;
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
public class ValidaEmailUnicoServiceTest {

    @InjectMocks
    private ValidaEmailUnicoService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve fazer nada se email for unico")
    void deveFazerNadaEmailUnico(){

        String email = "teste@cwi.com.br";
        when(usuarioRepository.existsByEmail(email)).thenReturn(false);

        tested.validar(email);

        verify(usuarioRepository).existsByEmail(email);
    }

    @Test
    @DisplayName("Deve retornar erro se email não for unico")
    void deveRetornarErro(){

        String email = "teste@cwi.com.br";

        doThrow(ResponseStatusException.class)
                .when(usuarioRepository).existsByEmail(email);

        assertThrows(ResponseStatusException.class, () -> {
            tested.validar(email);
        });

        verify(usuarioRepository).existsByEmail(email);
    }
}
