package br.com.cwi.api.controller.response;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncluirUsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private List<String> permissoes;
}
