package br.com.cwi.api.factories;

import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.security.domain.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostFactory {

    public static Post get() {
        Usuario usuario = new Usuario();
        List<Curtida> curtidas = new ArrayList<>();
        List<Comentario> comentarios = new ArrayList<>();

        return Post.builder()
                .id(SimpleFactory.getRandomLong())
                .dataPostagem(LocalDate.now())
                .descricao("post")
                .imagemUrl("url")
                .comentarios(comentarios)
                .curtidas(curtidas)
                .usuario(usuario)
                .build();
    }
}

