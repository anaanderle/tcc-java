package br.com.cwi.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class IncluirComentarioPostRequest {

    @NotNull
    private String descricao;
}
