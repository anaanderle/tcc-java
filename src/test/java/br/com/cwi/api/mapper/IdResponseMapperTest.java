package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.IdResponse;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IdResponseMapperTest {

    @Test
    @DisplayName("Deve retornar o id do usuario quando alterado")
    void deveRetornarIdUsuario() {

        Usuario usuario = UsuarioFactory.get();

        IdResponse response = IdResponseMapper.toResponse(usuario);

        assertEquals(usuario.getId(), response.getId());
    }

    @Test
    @DisplayName("Deve retornar o id do post quando alterado")
    void deveRetornarIdPost() {

        Post post = PostFactory.get();

        IdResponse response = IdResponseMapper.toResponse(post);

        assertEquals(post.getId(), response.getId());
    }
}
