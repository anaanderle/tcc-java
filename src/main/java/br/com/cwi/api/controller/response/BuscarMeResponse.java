package br.com.cwi.api.controller.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BuscarMeResponse {

    private Long id;
    private String nome;
    private String email;
    private String apelido;
    private LocalDate dataNascimento;
    private String imagemUrl;
    private boolean ativo;
    private List<PostResponse> posts;
}
