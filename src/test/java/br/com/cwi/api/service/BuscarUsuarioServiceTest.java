package br.com.cwi.api.service;

import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
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
import org.yaml.snakeyaml.scanner.ScannerImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarUsuarioServiceTest {

    @InjectMocks
    private BuscarUsuarioService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve retornar usuário")
    void deveRetornarUsuario(){

        Usuario usuario = UsuarioFactory.get();
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        Usuario response = tested.porId(usuario.getId());

        assertEquals(usuario.getId(), response.getId());
    }

    @Test
    @DisplayName("Nao deve retornar usuário inexistente")
    void naoDeveRetornarUsuario(){

        Long id = SimpleFactory.getRandomLong();
        doThrow(ResponseStatusException.class)
                .when(usuarioRepository).findById(id);

        assertThrows(ResponseStatusException.class, () -> {
            tested.porId(id);
        });
    }
}
