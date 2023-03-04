package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.CurtidaResponse;
import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;

public class CurtirPostMapper {
    public static Curtida toEntity(Post post, Usuario usuario) {

        return Curtida.builder()
                .post(post)
                .usuario(usuario)
                .build();
    }

    public static CurtidaResponse toResponse(Curtida curtida) {

        return CurtidaResponse.builder()
                .usuario(UsuarioResumidoMapper.toResponse(curtida.getUsuario()))
                .build();
    }
}
