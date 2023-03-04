package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.IniciarAmizadeResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.mapper.IniciarAmizadeMapper;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.ValidaAmigoAceitandoAmizadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.domain.SituacaoAmizade.*;
import static br.com.cwi.api.domain.SituacaoAmizade.SOLICITADA;
import static br.com.cwi.api.mapper.IniciarAmizadeMapper.toResponse;

@Service
public class IniciarAmizadeService {

    @Autowired
    private AmizadeRespository amizadeRespository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarAmizadeService buscarAmizadeService;

    @Autowired
    private ValidaAmigoAceitandoAmizadeValidator validaAmigoAceitandoAmizadeValidator;

    @Transactional
    public IniciarAmizadeResponse iniciarAmizade(Long id) {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        Amizade amizade = buscarAmizadeService.porSituacao(SOLICITADA, id);
        validaAmigoAceitandoAmizadeValidator.validar(amizade.getAmigo().getId(), usuarioAutenticado.getId());

        amizade.setSituacao(ATIVA);

        amizadeRespository.save(amizade);

        return toResponse(amizade);
    }
}
