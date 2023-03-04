package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.AmizadeResumidaResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.mapper.AmizadeResumidaMapper;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.cwi.api.domain.SituacaoAmizade.SOLICITADA;
import static br.com.cwi.api.mapper.AmizadeResumidaMapper.*;
import static java.util.stream.Collectors.*;

@Service
public class BuscarSolicitacoesService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AmizadeRespository amizadeRespository;

    public List<AmizadeResumidaResponse> buscarSolicitacoes() {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        List<Amizade> amizades = amizadeRespository.findAmizadesUsuarioPorSituacao(usuarioAutenticado, SOLICITADA);

        List<Amizade> amizadesFiltradas = amizades.stream()
                .filter(amizade -> amizade.getAmigo().equals(usuarioAutenticado))
                .collect(toList());

        return amizadesFiltradas.stream()
                .map(amizade -> toResponse(amizade, amizade.getUsuario()))
                .collect(toList());
    }
}
