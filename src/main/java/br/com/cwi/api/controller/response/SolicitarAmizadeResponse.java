package br.com.cwi.api.controller.response;

import br.com.cwi.api.domain.SituacaoAmizade;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SolicitarAmizadeResponse {

    private Long amizadeId;
    private SituacaoAmizade situacao;
}
