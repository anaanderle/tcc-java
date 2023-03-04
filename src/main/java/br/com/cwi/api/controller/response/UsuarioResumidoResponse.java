package br.com.cwi.api.controller.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResumidoResponse {

    private Long id;
    private String nome;
    private String email;
    private String imagemUrl;
}
