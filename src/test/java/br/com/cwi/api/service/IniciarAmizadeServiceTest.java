package br.com.cwi.api.service;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.factories.AmizadeFactory;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.ValidaAmigoAceitandoAmizadeValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import static br.com.cwi.api.domain.SituacaoAmizade.ATIVA;
import static br.com.cwi.api.domain.SituacaoAmizade.SOLICITADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IniciarAmizadeServiceTest {

    @InjectMocks
    private IniciarAmizadeService tested;

    @Mock
    private AmizadeRespository amizadeRespository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarAmizadeService buscarAmizadeService;

    @Mock
    private ValidaAmigoAceitandoAmizadeValidator validaAmigoAceitandoAmizadeValidator;

    @Captor
    private ArgumentCaptor<Amizade> amizadeCaptor;

    @Test
    @DisplayName("Deve iniciar amizade com sucesso")
    void deveIniciarAmizade(){

        Usuario usuario = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getSolicitada();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarAmizadeService.porSituacao(SOLICITADA, amizade.getId())).thenReturn(amizade);

        tested.iniciarAmizade(amizade.getId());

        verify(validaAmigoAceitandoAmizadeValidator).validar(amizade.getAmigo().getId(), usuario.getId());
        verify(amizadeRespository).save(amizadeCaptor.capture());
        assertEquals(amizade.getSituacao(), amizadeCaptor.getValue().getSituacao());
    }

    @Test
    @DisplayName("Nao deve iniciar amizade nao solicitada ou inexistente")
    void deveDeveIniciarAmizadeNaoSolicitadaOuInexistente(){

        Usuario usuario = UsuarioFactory.get();
        Long id = SimpleFactory.getRandomLong();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        doThrow(ResponseStatusException.class)
                .when(buscarAmizadeService).porSituacao(SOLICITADA, id);

        assertThrows(ResponseStatusException.class, () -> {
            tested.iniciarAmizade(id);
        });

        verifyNoInteractions(validaAmigoAceitandoAmizadeValidator, amizadeRespository);
    }

    @Test
    @DisplayName("Nao deve iniciar amizade sem permissao")
    void deveDeveIniciarAmizadeSemPermissao(){

        Usuario usuario = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getSolicitada();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarAmizadeService.porSituacao(SOLICITADA, amizade.getId())).thenReturn(amizade);

        doThrow(ResponseStatusException.class)
                .when(validaAmigoAceitandoAmizadeValidator).validar(amizade.getAmigo().getId(), usuario.getId());

        assertThrows(ResponseStatusException.class, () -> {
            tested.iniciarAmizade(amizade.getId());
        });

        verifyNoInteractions(amizadeRespository);
    }
}
