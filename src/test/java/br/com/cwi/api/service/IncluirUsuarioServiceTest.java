package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirUsuarioRequest;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IncluirUsuarioServiceTest {

    @InjectMocks
    private IncluirUsuarioService tested;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ValidaEmailUnicoService validaEmailUnicoService;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;

    @Test
    @DisplayName("Deve incluir usuário como ativo e com senha criptografada")
    void deveIncluirUsuario(){

        IncluirUsuarioRequest request = new IncluirUsuarioRequest();
        List<String> permissoes = new ArrayList<>();
        permissoes.add("USUARIO");
        request.setEmail("teste@cwi.com.br");
        request.setSenha("123456");
        request.setPermissoes(permissoes);

        tested.incluir(request);

        verify(validaEmailUnicoService).validar(request.getEmail());;
        verify(passwordEncoder).encode(request.getSenha());
        verify(usuarioRepository).save(usuarioCaptor.capture());

        Usuario usuario = usuarioCaptor.getValue();
        assertTrue(usuario.isAtivo());
    }

    @Test
    @DisplayName("Não deve incluir usuario se já existir algum com o mesmo email")
    void naoDeveIncluir(){

        IncluirUsuarioRequest request = new IncluirUsuarioRequest();
        List<String> permissoes = new ArrayList<>();
        permissoes.add("USUARIO");
        request.setEmail("teste@cwi.com.br");
        request.setSenha("123456");
        request.setPermissoes(permissoes);

        doThrow(ResponseStatusException.class)
                .when(validaEmailUnicoService).validar(request.getEmail());

        assertThrows(ResponseStatusException.class, () -> {
            tested.incluir(request);
        });

        verifyNoInteractions(usuarioRepository);
    }
}
