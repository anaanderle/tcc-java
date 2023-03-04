package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.BuscarMeResponse;
import br.com.cwi.api.controller.response.IncluirUsuarioResponse;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UsuarioMeMapperTest {

    @Test
    @DisplayName("Deve retornar infos quando alterado")
    void deveRetornarInfos() {

        Usuario usuario = UsuarioFactory.get();

        BuscarMeResponse response = UsuarioMeMapper.toResponse(usuario);

        assertEquals(usuario.getId(), response.getId());
        assertEquals(usuario.getNome(), response.getNome());
        assertEquals(usuario.getApelido(), response.getApelido());
        assertEquals(usuario.getDataNascimento(), response.getDataNascimento());
        assertEquals(usuario.getImagemUrl(), response.getImagemUrl());
        assertEquals(usuario.isAtivo(), response.isAtivo());
    }
}
