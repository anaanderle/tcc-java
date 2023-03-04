package br.com.cwi.api.validator;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.factories.AmizadeFactory;
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
public class ValidaUsuarioDesfazendoAmizadeValidatorTest {

    @InjectMocks
    private ValidaUsuarioDesfazendoAmizadeValidator tested;

    @Test
    @DisplayName("Deve fazer nada se usuario é válido")
    void deveFazerNada(){

        Usuario usuario = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.getAmigo(usuario);

        tested.validar(amizade, usuario);
    }

    @Test
    @DisplayName("Deve dar erro se usuario é invalido")
    void deveDarErro(){

        Usuario usuario1 = UsuarioFactory.get();
        Usuario usuario2 = UsuarioFactory.get();
        Usuario amigo = UsuarioFactory.get();
        Amizade amizade = AmizadeFactory.get(usuario1, usuario2);

        assertThrows(ResponseStatusException.class, () -> {
            tested.validar(amizade, amigo);
        });
    }
}
