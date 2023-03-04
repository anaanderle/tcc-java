package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.domain.Post;

import java.util.stream.Collectors;

public class PostMapper {
    public static PostResponse toResponse(Post post) {

        return PostResponse.builder()
                .id(post.getId())
                .descricao(post.getDescricao())
                .usuario(UsuarioResumidoMapper.toResponse(post.getUsuario()))
                .imagemUrl(post.getImagemUrl())
                .curtidas(post.getCurtidas().stream().map(curtida -> CurtirPostMapper.toResponse(curtida)).collect(Collectors.toList()))
                .comentario(post.getComentarios().stream().map(comentario -> ComentarPostMapper.toResponse(comentario)).collect(Collectors.toList()))
                .build();
    }
}
