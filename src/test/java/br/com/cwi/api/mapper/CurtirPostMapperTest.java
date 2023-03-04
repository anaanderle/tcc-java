package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.ComentarioResponse;
import br.com.cwi.api.controller.response.CurtidaResponse;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.factories.ComentarioFactory;
import br.com.cwi.api.factories.CurtidaFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CurtirPostMapperTest {

    @Test
    @DisplayName("Deve retornar infos quando alterado")
    void deveRetornarInfos() {

        Usuario usuario = UsuarioFactory.get();
        Curtida curtida = CurtidaFactory.get();
        curtida.setUsuario(usuario);

        CurtidaResponse response = CurtirPostMapper.toResponse(curtida);

        assertEquals(curtida.getUsuario().getNome(), response.getUsuario().getNome());
    }
}
