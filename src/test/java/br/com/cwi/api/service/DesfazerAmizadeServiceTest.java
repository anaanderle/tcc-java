package br.com.cwi.api.service;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.factories.AmizadeFactory;
import br.com.cwi.api.factories.SimpleFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.ValidaUsuarioDesfazendoAmizadeValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import static br.com.cwi.api.domain.SituacaoAmizade.ATIVA;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DesfazerAmizadeServiceTest {

    @InjectMocks
    private DesfazerAmizadeService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private BuscarAmizadeService buscarAmizadeService;

    @Mock
    private ValidaUsuarioDesfazendoAmizadeValidator validaUsuarioDesfazendoAmizadeValidator;

    @Mock
    private AmizadeRespository amizadeRespository;

    @Test
    @DisplayName("Deve desfazer amizade com sucesso")
    void deveDesfazerAmizade(){

        Usuario usuario = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getAtiva();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarAmizadeService.porSituacao(ATIVA, amizade.getId())).thenReturn(amizade);

        tested.desfazerAmizade(amizade.getId());

        verify(validaUsuarioDesfazendoAmizadeValidator).validar(amizade, usuario);
        verify(amizadeRespository).deleteById(amizade.getId());
    }

    @Test
    @DisplayName("Nao deve desfazer amizade inexistente")
    void naoDeveDesfazerAmizadeInexistente(){

        Usuario usuario = UsuarioFactory.get();
        Long id = SimpleFactory.getRandomLong();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        doThrow(ResponseStatusException.class)
                .when(buscarAmizadeService).porSituacao(ATIVA, id);

        assertThrows(ResponseStatusException.class, () -> {
            tested.desfazerAmizade(id);
        });

        verifyNoInteractions(validaUsuarioDesfazendoAmizadeValidator, amizadeRespository);
    }

    @Test
    @DisplayName("Nao deve desfazer amizade se usuario sem permissao")
    void naoDeveDesfazerAmizadeUsuarioSemPermissao(){

        Usuario usuario = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getAtiva();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(buscarAmizadeService.porSituacao(ATIVA, amizade.getId())).thenReturn(amizade);

        doThrow(ResponseStatusException.class)
                .when(validaUsuarioDesfazendoAmizadeValidator).validar(amizade, usuario);

        assertThrows(ResponseStatusException.class, () -> {
            tested.desfazerAmizade(amizade.getId());
        });

        verifyNoInteractions(amizadeRespository);
    }
}
