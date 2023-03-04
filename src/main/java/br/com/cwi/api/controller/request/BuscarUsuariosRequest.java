package br.com.cwi.api.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BuscarUsuariosRequest {

    @NotNull
    private String buscar;
}
