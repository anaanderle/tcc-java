package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.AmizadeResumidaResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.factories.AmizadeFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AmizadeResumidaMapperTest {

    @Test
    @DisplayName("Deve retornar infos quando alterado")
    void deveRetornarInfos() {

        Usuario usuario = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getSolicitada();

        AmizadeResumidaResponse response = AmizadeResumidaMapper.toResponse(amizade, usuario);

        assertEquals(amizade.getSituacao(), response.getSituacao());
    }
}
