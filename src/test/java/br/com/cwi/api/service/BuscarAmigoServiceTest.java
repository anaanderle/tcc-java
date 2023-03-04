package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.AmizadeResumidaResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.api.domain.SituacaoAmizade.ATIVA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuscarAmigoServiceTest {

    @InjectMocks
    private BuscarAmigosService tested;

    @Mock
    private AmizadeRespository amizadeRespository;

    @Mock
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Test
    @DisplayName("Deve buscar todas as amizades ativas")
    void deveBuscarAmizadesAtivas(){

        Usuario usuario = UsuarioFactory.get();
        List<Amizade> amizades = List.of(
                AmizadeFactory.getAtiva(),
                AmizadeFactory.getAtiva(),
                AmizadeFactory.getAtiva()
        );

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(amizadeRespository.findAmizadesUsuarioPorSituacao(usuario, ATIVA)).thenReturn(amizades);

        List<AmizadeResumidaResponse> response = tested.buscarAmigos();

        verify(amizadeRespository).findAmizadesUsuarioPorSituacao(usuario, ATIVA);
        assertEquals(amizades.size(), response.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não encontrar amizades")
    void deveRetornarVazio(){

        Usuario usuario = UsuarioFactory.get();
        List<Amizade> amizades = new ArrayList<>();

        when(usuarioAutenticadoService.get()).thenReturn(usuario);
        when(amizadeRespository.findAmizadesUsuarioPorSituacao(usuario, ATIVA)).thenReturn(amizades);

        List<AmizadeResumidaResponse> response = tested.buscarAmigos();

        verify(amizadeRespository).findAmizadesUsuarioPorSituacao(usuario, ATIVA);
        assertEquals(amizades.size(), response.size());
    }
}
