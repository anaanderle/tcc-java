package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.AmizadeResumidaResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.factories.AmizadeFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.cwi.api.domain.SituacaoAmizade.ATIVA;
import static br.com.cwi.api.domain.SituacaoAmizade.SOLICITADA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarSolicitacoesServiceTest {

    @InjectMocks
    private BuscarSolicitacoesService tested;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Mock
    private AmizadeRespository amizadeRespository;

    @Test
    @DisplayName("Deve buscar todas as amizades solicitadas")
    void deveBuscarAmizadesSolicitadas(){

        Usuario usuario = UsuarioFactory.get();
        List<Amizade> amizades = List.of(
                AmizadeFactory.getAmigo(usuario),
                AmizadeFactory.getAmigo(usuario),
                AmizadeFactory.getAmigo(usuario)
        );
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(amizadeRespository.findAmizadesUsuarioPorSituacao(usuario, SOLICITADA)).thenReturn(amizades);

        List<AmizadeResumidaResponse> response = tested.buscarSolicitacoes();

        verify(amizadeRespository).findAmizadesUsuarioPorSituacao(usuario, SOLICITADA);
        assertEquals(amizades.size(), response.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não encontrar amizades solicitadas")
    void deveRetornarVazio(){

        Usuario usuario = UsuarioFactory.get();
        List<Amizade> amizades = new ArrayList<>();
        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(amizadeRespository.findAmizadesUsuarioPorSituacao(usuario, SOLICITADA)).thenReturn(amizades);

        List<AmizadeResumidaResponse> response = tested.buscarSolicitacoes();

        verify(amizadeRespository).findAmizadesUsuarioPorSituacao(usuario, SOLICITADA);
        assertEquals(amizades.size(), response.size());
    }
}
