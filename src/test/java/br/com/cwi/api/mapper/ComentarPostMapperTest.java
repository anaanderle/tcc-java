package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.AmizadeResumidaResponse;
import br.com.cwi.api.controller.response.ComentarioResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.factories.AmizadeFactory;
import br.com.cwi.api.factories.ComentarioFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ComentarPostMapperTest {

    @Test
    @DisplayName("Deve retornar infos quando alterado")
    void deveRetornarInfos() {

        Usuario usuario = UsuarioFactory.get();
        Comentario comentario = ComentarioFactory.get();

        ComentarioResponse response = ComentarPostMapper.toResponse(comentario);

        assertEquals(comentario.getDescricao(), response.getDescricao());
    }
}
