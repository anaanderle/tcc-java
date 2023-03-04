package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.UsuarioResumidoResponse;
import br.com.cwi.api.security.domain.Usuario;

public class UsuarioResumidoMapper {

    public static UsuarioResumidoResponse toResponse(Usuario usuario) {

        return UsuarioResumidoResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .imagemUrl(usuario.getImagemUrl())
                .build();
    }
}
