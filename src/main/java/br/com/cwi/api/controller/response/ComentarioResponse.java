package br.com.cwi.api.controller.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ComentarioResponse {

    private UsuarioResumidoResponse usuario;
    private String descricao;
}
