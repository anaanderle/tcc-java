package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.ComentarioResponse;
import br.com.cwi.api.domain.Comentario;

public class ComentarPostMapper {

    public static ComentarioResponse toResponse(Comentario comentario) {

        return ComentarioResponse.builder()
                .usuario(UsuarioResumidoMapper.toResponse(comentario.getUsuario()))
                .descricao(comentario.getDescricao())
                .build();
    }
}
