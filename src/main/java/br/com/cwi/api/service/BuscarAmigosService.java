package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.AmizadeResumidaResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.cwi.api.domain.SituacaoAmizade.ATIVA;
import static br.com.cwi.api.mapper.AmizadeResumidaMapper.*;
import static java.util.stream.Collectors.*;

@Service
public class BuscarAmigosService {

    @Autowired
    private AmizadeRespository amizadeRespository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public List<AmizadeResumidaResponse> buscarAmigos() {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        List<Amizade> amizades = amizadeRespository.findAmizadesUsuarioPorSituacao(usuarioAutenticado, ATIVA);

        return amizades.stream()
                .map(amizade -> {
                    if(amizade.getUsuario().equals(usuarioAutenticado)){
                        return toResponse(amizade, amizade.getAmigo());
                    }
                    return toResponse(amizade, amizade.getUsuario());
                })
                .collect(toList());
    }
}
