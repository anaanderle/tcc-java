package br.com.cwi.api.controller.response;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {

    private Long id;
    private String imagemUrl;
    private String descricao;
    private UsuarioResumidoResponse usuario;
    private List<CurtidaResponse> curtidas;
    private List<ComentarioResponse> comentario;
}
