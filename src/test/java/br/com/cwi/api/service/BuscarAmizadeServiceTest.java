package br.com.cwi.api.service;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.factories.AmizadeFactory;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static br.com.cwi.api.domain.SituacaoAmizade.ATIVA;
import static br.com.cwi.api.domain.SituacaoAmizade.SOLICITADA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarAmizadeServiceTest {

    @InjectMocks
    private BuscarAmizadeService tested;

    @Mock
    private AmizadeRespository amizadeRespository;

    @Test
    @DisplayName("Deve retornar amizade quando situação ATIVA")
    void deveRetornarAmizadeAtiva(){

        Amizade amizade = AmizadeFactory.getAtiva();
        when(amizadeRespository.findByIdAndSituacao(amizade.getId(), ATIVA)).thenReturn(Optional.of(amizade));

        Amizade response = tested.porSituacao(ATIVA, amizade.getId());

        verify(amizadeRespository).findByIdAndSituacao(amizade.getId(), ATIVA);
        assertEquals(amizade, response);
    }

    @Test
    @DisplayName("Deve retornar amizade quando situação SOLICITADA")
    void deveRetornarAmizadeSolicitada(){

        Amizade amizade = AmizadeFactory.getSolicitada();
        when(amizadeRespository.findByIdAndSituacao(amizade.getId(), SOLICITADA)).thenReturn(Optional.of(amizade));

        Amizade response = tested.porSituacao(SOLICITADA, amizade.getId());

        verify(amizadeRespository).findByIdAndSituacao(amizade.getId(), SOLICITADA);
        assertEquals(amizade, response);
    }

    @Test
    @DisplayName("Deve dar exception quando amizade ATIVA não existir")
    void naoDeveRetornarAmizadeAtiva(){

        Long id = SimpleFactory.getRandomLong();
        doThrow(ResponseStatusException.class)
                .when(amizadeRespository).findByIdAndSituacao(id, ATIVA);

        assertThrows(ResponseStatusException.class, () -> {
            tested.porSituacao(ATIVA, id);
        });

        verify(amizadeRespository).findByIdAndSituacao(id, ATIVA);
    }

    @Test
    @DisplayName("Deve dar exception quando amizade SOLICITADA não existir")
    void naoDeveRetornarAmizadeSolicitada(){

        Long id = SimpleFactory.getRandomLong();
        doThrow(ResponseStatusException.class)
                .when(amizadeRespository).findByIdAndSituacao(id, SOLICITADA);

        assertThrows(ResponseStatusException.class, () -> {
            tested.porSituacao(SOLICITADA, id);
        });

        verify(amizadeRespository).findByIdAndSituacao(id, SOLICITADA);
    }

    @Test
    @DisplayName("Deve retornar true quando existir amizade")
    void deveRetornarTrue(){

        Usuario usuario1 = UsuarioFactory.get();
        Usuario usuario2 = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getAtiva();

        when(amizadeRespository.existsByUsuarioAndAmigoAndSituacao(usuario1, usuario2, ATIVA)).thenReturn(true);

        boolean response = tested.validar(usuario1, usuario2);

        verify(amizadeRespository).existsByUsuarioAndAmigoAndSituacao(usuario1, usuario2, ATIVA);
        assertTrue(response);
    }

    @Test
    @DisplayName("Deve retornar false quando não existir amizade")
    void deveRetornarFalse(){

        Usuario usuario1 = UsuarioFactory.get();
        Usuario usuario2 = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getSolicitada();

        when(amizadeRespository.existsByUsuarioAndAmigoAndSituacao(usuario1, usuario2, ATIVA)).thenReturn(false);

        boolean response = tested.validar(usuario1, usuario2);

        verify(amizadeRespository).existsByUsuarioAndAmigoAndSituacao(usuario1, usuario2, ATIVA);
        assertFalse(response);
    }
}
