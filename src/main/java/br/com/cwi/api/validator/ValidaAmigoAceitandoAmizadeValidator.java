package br.com.cwi.api.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class ValidaAmigoAceitandoAmizadeValidator {

    final String ERRO_ACEITAR_AMIZADE = "Você não possui permissões para aceitar essa amizade";

    public void validar(Long amigoId, Long usuarioId){

        if(!amigoId.equals(usuarioId)){
            throw new ResponseStatusException(BAD_REQUEST, ERRO_ACEITAR_AMIZADE);
        }
    }
}
