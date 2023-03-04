package br.com.cwi.api.controller.request;

import br.com.cwi.api.domain.SituacaoPost;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class IncluirPostRequest {

    private String imagemUrl;
    @NotBlank
    private String descricao;
    @NotNull
    private SituacaoPost situacao;
}
