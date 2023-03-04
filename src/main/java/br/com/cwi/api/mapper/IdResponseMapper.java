package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.IdResponse;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;

public class IdResponseMapper {

    public static IdResponse toResponse(Usuario usuario) {
        return IdResponse.builder()
                .id(usuario.getId())
                .build();
    }

    public static IdResponse toResponse(Post post) {
        return IdResponse.builder()
                .id(post.getId())
                .build();
    }
}
