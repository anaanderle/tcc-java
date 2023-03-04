package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.BuscarMeResponse;
import br.com.cwi.api.security.domain.Usuario;

import java.util.stream.Collectors;

public class UsuarioMeMapper {

    public static BuscarMeResponse toResponse(Usuario usuario) {

        return BuscarMeResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .apelido(usuario.getApelido())
                .dataNascimento(usuario.getDataNascimento())
                .imagemUrl(usuario.getImagemUrl())
                .ativo(usuario.isAtivo())
                .posts(usuario.getPosts().stream().map(post -> PostMapper.toResponse(post)).collect(Collectors.toList()))
                .build();
    }
}
