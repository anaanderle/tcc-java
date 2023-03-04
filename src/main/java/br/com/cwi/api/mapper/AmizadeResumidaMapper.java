package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.AmizadeResumidaResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.security.domain.Usuario;

public class AmizadeResumidaMapper {

    public static AmizadeResumidaResponse toResponse(Amizade amizade, Usuario usuario) {

        return AmizadeResumidaResponse.builder()
                .id(amizade.getId())
                .usuario(UsuarioResumidoMapper.toResponse(usuario))
                .situacao(amizade.getSituacao())
                .build();
    }
}
