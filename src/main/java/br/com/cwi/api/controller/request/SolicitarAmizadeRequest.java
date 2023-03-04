package br.com.cwi.api.controller.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SolicitarAmizadeRequest {

    @NotNull
    private Long amigoId;
}
