package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.BuscarMeResponse;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarMeServiceTest {

    @InjectMocks
    private BuscarMeService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test
    @DisplayName("Deve retornar informações do usuário")
    void deveRetornarInformacoes(){

        Usuario usuario = UsuarioFactory.get();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        BuscarMeResponse response = tested.buscar();

        assertEquals(usuario.getId(), response.getId());
    }
}
