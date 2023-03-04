package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.IncluirUsuarioResponse;
import br.com.cwi.api.controller.response.UsuarioResumidoResponse;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsuarioResumidoMapperTest {

    @Test
    @DisplayName("Deve retornar infos quando alterado")
    void deveRetornarInfos() {

        Usuario usuario = UsuarioFactory.get();

        UsuarioResumidoResponse response = UsuarioResumidoMapper.toResponse(usuario);

        assertEquals(usuario.getNome(), response.getNome());
        assertEquals(usuario.getEmail(), response.getEmail());
        assertEquals(usuario.getImagemUrl(), response.getImagemUrl());
    }
}
