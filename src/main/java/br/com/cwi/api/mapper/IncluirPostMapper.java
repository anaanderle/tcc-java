package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.domain.Post;

public class IncluirPostMapper {

    public static Post toEntity(IncluirPostRequest request) {

        return Post.builder()
                .descricao(request.getDescricao())
                .imagemUrl(request.getImagemUrl())
                .situacao(request.getSituacao())
                .build();
    }
}
