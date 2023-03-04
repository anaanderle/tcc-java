package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.AlterarUsuarioRequest;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
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
public class AlterarUsuarioServiceTest {

    @InjectMocks
    private AlterarUsuarioService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;

    @Test
    @DisplayName("Deve alterar informações do usuário")
    void deveAlterarUsuario(){

        Usuario usuario = UsuarioFactory.get();
        AlterarUsuarioRequest request = new AlterarUsuarioRequest();
        request.setNome("Teste");
        request.setApelido("teste.teste");

        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        tested.alterar(request);

        verify(usuarioRepository).save(usuarioCaptor.capture());

        assertEquals(request.getNome(), usuarioCaptor.getValue().getNome());
        assertEquals(request.getApelido(), usuarioCaptor.getValue().getApelido());
        assertEquals(request.getImagemUrl(), usuarioCaptor.getValue().getImagemUrl());
    }

    @Test
    @DisplayName("Nao deve alterar usuario inexistente")
    void naoDeveAlterarUsuarioInexistente(){

        Long id = SimpleFactory.getRandomLong();
        AlterarUsuarioRequest request = new AlterarUsuarioRequest();
        doThrow(ResponseStatusException.class).when(usuarioAutenticadoService).get();

        assertThrows(ResponseStatusException.class, () -> {
            tested.alterar(request);
        });

        verifyNoInteractions(usuarioRepository);
    }
}
