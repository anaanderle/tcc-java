package br.com.cwi.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AlterarUsuarioRequest {

    @NotBlank
    private String nome;
    private String apelido;
    private String imagemUrl;
}
