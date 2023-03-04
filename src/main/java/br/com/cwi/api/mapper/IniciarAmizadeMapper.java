package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.IniciarAmizadeResponse;
import br.com.cwi.api.domain.Amizade;

public class IniciarAmizadeMapper {

    public static IniciarAmizadeResponse toResponse(Amizade amizade) {

        return IniciarAmizadeResponse.builder()
                .amizadeId(amizade.getId())
                .situacao(amizade.getSituacao())
                .build();
    }
}
