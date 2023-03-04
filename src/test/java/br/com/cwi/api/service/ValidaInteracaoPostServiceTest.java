package br.com.cwi.api.service;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.factories.PostFactory;
import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static br.com.cwi.api.domain.SituacaoAmizade.ATIVA;
import static br.com.cwi.api.domain.SituacaoPost.PRIVADO;
import static br.com.cwi.api.domain.SituacaoPost.PUBLICO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidaInteracaoPostServiceTest {

    @InjectMocks
    private ValidaInteracaoPostService tested;

    @Mock
    private AmizadeRespository amizadeRespository;

    @Test
    @DisplayName("Deve fazer nada se post for publico")
    void deveFazerNadaPostPublico() {

        Usuario usuario = UsuarioFactory.get();
        Post post = PostFactory.get();
        post.setSituacao(PUBLICO);

        tested.validar(post, usuario);

        verifyNoInteractions(amizadeRespository);
    }
}
