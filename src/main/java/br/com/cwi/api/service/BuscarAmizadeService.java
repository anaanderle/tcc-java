package br.com.cwi.api.service;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static br.com.cwi.api.domain.SituacaoAmizade.ATIVA;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BuscarAmizadeService {

    final String ERRO_AMIZADE_INVALIDA = "Amizade não existe ou você não tem permissões";
    @Autowired
    private AmizadeRespository amizadeRespository;

    public Amizade porSituacao(SituacaoAmizade situacaoDesejada, Long id) {

        return amizadeRespository.findByIdAndSituacao(id, situacaoDesejada)
                .orElseThrow(
                        () -> new ResponseStatusException(BAD_REQUEST, ERRO_AMIZADE_INVALIDA)
                );
    }

    public boolean validar(Usuario usuario1, Usuario usuario2) {

        return amizadeRespository.existsByUsuarioAndAmigoAndSituacao(usuario1, usuario2, ATIVA) ||
                amizadeRespository.existsByUsuarioAndAmigoAndSituacao(usuario2, usuario1, ATIVA);

    }
}
