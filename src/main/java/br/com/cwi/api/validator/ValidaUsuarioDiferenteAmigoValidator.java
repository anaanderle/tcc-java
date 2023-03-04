package br.com.cwi.api.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ValidaUsuarioDiferenteAmigoValidator {

    final String ERRO_USUARIO_AMIGO_UNICO = "Você não pode se adicionar como amigo";

    public void validar(Long usuarioId, Long amigoId) {

        if(usuarioId.equals(amigoId)){
            throw new ResponseStatusException(BAD_REQUEST, ERRO_USUARIO_AMIGO_UNICO);
        }
    }
}
