package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.SolicitarAmizadeRequest;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.ValidaUsuarioDiferenteAmigoValidator;
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

import static br.com.cwi.api.domain.SituacaoAmizade.SOLICITADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SolicitarAmizadeServiceTest {

    @InjectMocks
    private SolicitarAmizadeService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private AmizadeRespository amizadeRepository;

    @Mock
    private BuscarUsuarioService buscarUsuarioService;

    @Mock
    private ValidaUsuarioDiferenteAmigoValidator validaUsuarioDiferenteAmigoValidator;

    @Mock
    private ValidaSituacaoAmizadeService validaSituacaoAmizadeService;

    @Captor
    private ArgumentCaptor<Amizade> amizadeCaptor;

    @Test
    @DisplayName("Deve solicitar amizade com sucesso")
    void deveSolicitarAmizade(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        SolicitarAmizadeRequest request = new SolicitarAmizadeRequest();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(request.getAmigoId())).thenReturn(amigo);

        tested.solicitarAmizade(request);

        verify(amizadeRepository).save(amizadeCaptor.capture());
        verify(validaUsuarioDiferenteAmigoValidator).validar(usuario.getId(), amigo.getId());
        verify(validaSituacaoAmizadeService).validarSemAmizade(usuario, amigo);
        assertEquals(SOLICITADA, amizadeCaptor.getValue().getSituacao());
    }

    @Test
    @DisplayName("Nao deve solicitar amizade para usuario inexistente")
    void naoDeveSolicitarAmizadeUsuarioInexistente(){

        Usuario usuario = UsuarioFactory.get();
        Long id = SimpleFactory.getRandomLong();
        SolicitarAmizadeRequest request = new SolicitarAmizadeRequest();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);

        doThrow(ResponseStatusException.class)
                .when(buscarUsuarioService).porId(request.getAmigoId());

        assertThrows(ResponseStatusException.class, () -> {
            tested.solicitarAmizade(request);
        });

        verifyNoInteractions(validaUsuarioDiferenteAmigoValidator, validaSituacaoAmizadeService, amizadeRepository);
    }

    @Test
    @DisplayName("Nao deve solicitar amizade para amigo igual ao usuario")
    void naoDeveSeAutoSolicitar(){

        Usuario usuario = UsuarioFactory.get();
        SolicitarAmizadeRequest request = new SolicitarAmizadeRequest();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(request.getAmigoId())).thenReturn(usuario);

        doThrow(ResponseStatusException.class)
                .when(validaUsuarioDiferenteAmigoValidator).validar(usuario.getId(), usuario.getId());

        assertThrows(ResponseStatusException.class, () -> {
            tested.solicitarAmizade(request);
        });

        verifyNoInteractions(validaSituacaoAmizadeService, amizadeRepository);
    }

    @Test
    @DisplayName("Nao deve solicitar amizade para uma relação já existente")
    void naoDeveSolicitarAmizadeRelacaoExistente(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        SolicitarAmizadeRequest request = new SolicitarAmizadeRequest();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarUsuarioService.porId(request.getAmigoId())).thenReturn(amigo);

        doThrow(ResponseStatusException.class)
                .when(validaSituacaoAmizadeService).validarSemAmizade(usuario, amigo);

        assertThrows(ResponseStatusException.class, () -> {
            tested.solicitarAmizade(request);
        });

        verifyNoInteractions(amizadeRepository);
    }
}