package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.UsuarioResumidoResponse;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarUsuariosServiceTest {

    @InjectMocks
    private BuscarUsuariosService tested;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve retornar lista de usuarios")
    void deveRetornarUsuarios(){

        String request = "request";
        List<Usuario> usuarios = List.of(
                UsuarioFactory.get(),
                UsuarioFactory.get(),
                UsuarioFactory.get()
        );
        when(usuarioRepository.findByNomeContainingOrEmailContainingAllIgnoreCase(request, request))
                .thenReturn(usuarios);

        List<UsuarioResumidoResponse> response = tested.buscarUsuarios(request);

        assertEquals(usuarios.size(), response.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia")
    void deveRetornarListaVazia(){

        String request = "request";
        List<Usuario> usuarios = new ArrayList<>();
        when(usuarioRepository.findByNomeContainingOrEmailContainingAllIgnoreCase(request, request))
                .thenReturn(usuarios);

        List<UsuarioResumidoResponse> response = tested.buscarUsuarios(request);

        assertEquals(usuarios.size(), response.size());
    }
}
