package br.com.cwi.api.factories;

import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;

public class ComentarioFactory {

    public static Comentario get(){
        Post post = PostFactory.get();
        Usuario usuario = UsuarioFactory.get();

        return Comentario.builder()
                .usuario(usuario)
                .post(post)
                .descricao("comentario teste")
                .build();
    }
}
