package br.com.cwi.api.service;

import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ValidaSituacaoAmizadeService {

    final String ERRO_NAO_PODE_SOLICITAR_AMIZADE = "Não é possível solicitar amizade";

    @Autowired
    private AmizadeRespository amizadeRespository;

    public void validarSemAmizade(Usuario usuario, Usuario amigo) {

        if(amizadeRespository.existsByUsuarioAndAmigo(usuario, amigo) ||
                amizadeRespository.existsByUsuarioAndAmigo(amigo, usuario)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ERRO_NAO_PODE_SOLICITAR_AMIZADE);
        }
    }
}
