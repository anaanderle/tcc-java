package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirComentarioPostRequest;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;

public class IncluirComentarioPostMapper {
    public static Comentario toEntity(IncluirComentarioPostRequest request, Post post, Usuario usuario) {

        return Comentario.builder()
                .descricao(request.getDescricao())
                .post(post)
                .usuario(usuario)
                .build();
    }
}
