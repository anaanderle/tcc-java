package br.com.cwi.api.mapper;

import br.com.cwi.api.controller.response.IncluirUsuarioResponse;
import br.com.cwi.api.controller.response.PostResponse;
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
public class PostMapperTest {

    @Test
    @DisplayName("Deve retornar infos quando alterado")
    void deveRetornarInfos() {

        Post post = PostFactory.get();

        PostResponse response = PostMapper.toResponse(post);

        assertEquals(post.getDescricao(), response.getDescricao());
        assertEquals(post.getImagemUrl(), response.getImagemUrl());
    }
}
