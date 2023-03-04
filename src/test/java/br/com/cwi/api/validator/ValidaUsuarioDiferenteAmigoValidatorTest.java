package br.com.cwi.api.validator;

import br.com.cwi.api.factories.UsuarioFactory;
import br.com.cwi.api.security.domain.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidaUsuarioDiferenteAmigoValidatorTest {

    @InjectMocks
    private ValidaUsuarioDiferenteAmigoValidator tested;

    @Test
    @DisplayName("Deve fazer nada se usuario e amigo diferentes")
    void deveFazerNada(){

        Usuario usuario = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();

        tested.validar(usuario.getId(), amigo.getId());
    }

    @Test
    @DisplayName("Deve dar erro se usuario e amigo iguais")
    void deveDarErro(){

        Usuario usuario = UsuarioFactory.get();

        assertThrows(ResponseStatusException.class, () -> {
            tested.validar(usuario.getId(), usuario.getId());
        });
    }
}
