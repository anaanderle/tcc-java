package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.IncluirUsuarioResponse;
import br.com.cwi.api.controller.response.SolicitarAmizadeResponse;
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
public class SolicitarAmizadeMapperTest {

    @Test
    @DisplayName("Deve retornar infos quando alterado")
    void deveRetornarInfos() {

        Amizade amizade = AmizadeFactory.getAtiva();

        SolicitarAmizadeResponse response = SolicitarAmizadeMapper.toResponse(amizade);

        assertEquals(amizade.getId(), response.getAmizadeId());
        assertEquals(amizade.getSituacao(), response.getSituacao());
    }
}
