package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.IniciarAmizadeResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.factories.AmizadeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IniciarAmizadeMapperTest {

    @Test
    @DisplayName("Deve retornar infos quando alterado")
    void deveRetornarInfos() {

        Amizade amizade = AmizadeFactory.getAtiva();

        IniciarAmizadeResponse response = IniciarAmizadeMapper.toResponse(amizade);

        assertEquals(amizade.getId(), response.getAmizadeId());
        assertEquals(amizade.getSituacao(), response.getSituacao());
    }
}
