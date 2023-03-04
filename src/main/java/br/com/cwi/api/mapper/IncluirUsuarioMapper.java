package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.request.IncluirUsuarioRequest;
import br.com.cwi.api.controller.response.IncluirUsuarioResponse;
import br.com.cwi.api.security.domain.Permissao;
import br.com.cwi.api.security.domain.Usuario;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class IncluirUsuarioMapper {

    public static Usuario toEntity(IncluirUsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setApelido(request.getApelido());
        entity.setDataNascimento(request.getDataNascimento());
        entity.setImagemUrl(request.getImagemUrl());
        return entity;
    }

    public static IncluirUsuarioResponse toResponse(Usuario entity) {
        return IncluirUsuarioResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .permissoes(buildPermissoesResponse(entity.getPermissoes()))
                .build();
    }

    private static List<String> buildPermissoesResponse(List<Permissao> permissoes) {
        return permissoes.stream()
                .map(Permissao::getNome)
                .collect(toList());
    }
}
