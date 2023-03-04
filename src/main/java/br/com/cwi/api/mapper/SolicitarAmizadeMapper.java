package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.SolicitarAmizadeResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.security.domain.Usuario;

public class SolicitarAmizadeMapper {

    public static Amizade toEntity(Usuario usuario, Usuario amigo) {

        return Amizade.builder()
                .usuario(usuario)
                .amigo(amigo)
                .build();
    }

    public static SolicitarAmizadeResponse toResponse(Amizade amizade) {

        return SolicitarAmizadeResponse.builder()
                .amizadeId(amizade.getId())
                .situacao(amizade.getSituacao())
                .build();
    }
}
