package br.com.cwi.api.controller.response;

import br.com.cwi.api.domain.SituacaoAmizade;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AmizadeResumidaResponse {

    private Long id;
    private UsuarioResumidoResponse usuario;
    private SituacaoAmizade situacao;
}
