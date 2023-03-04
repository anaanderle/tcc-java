package br.com.cwi.api.validator;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ValidaUsuarioDesfazendoAmizadeValidator {

    final String ERRO_DESFAZER_AMIZADE = "Você não possui permissões para desfazer essa amizade";

    public void validar(Amizade amizade, Usuario usuarioAutenticado) {

        if(!amizade.getAmigo().getId().equals(usuarioAutenticado.getId())){
            if(!amizade.getUsuario().getId().equals(usuarioAutenticado.getId())){
                throw new ResponseStatusException(BAD_REQUEST, ERRO_DESFAZER_AMIZADE);
            }
        }
    }
}
