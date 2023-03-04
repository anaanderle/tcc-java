package br.com.cwi.api.factories;

import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;

public class CurtidaFactory {

    public static Curtida get(){
        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();

        return Curtida.builder()
                .id(SimpleFactory.getRandomLong())
                .usuario(usuario)
                .post(post)
                .build();
    }
}
