package br.com.cwi.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class IncluirUsuarioRequest {

    @NotBlank
    private String nome;
    @NotBlank @Email
    private String email;
    private String apelido;
    @NotBlank
    private String senha;
    private String imagemUrl;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull @NotEmpty
    private List<String> permissoes;
}
