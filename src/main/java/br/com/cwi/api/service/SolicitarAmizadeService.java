package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.SolicitarAmizadeRequest;
import br.com.cwi.api.controller.response.SolicitarAmizadeResponse;
import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.mapper.SolicitarAmizadeMapper;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.ValidaUsuarioDiferenteAmigoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.domain.SituacaoAmizade.*;
import static br.com.cwi.api.mapper.SolicitarAmizadeMapper.*;

@Service
public class SolicitarAmizadeService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AmizadeRespository amizadeRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private ValidaUsuarioDiferenteAmigoValidator validaUsuarioDiferenteAmigoValidator;

    @Autowired
    private ValidaSituacaoAmizadeService validaSituacaoAmizadeService;

    @Transactional
    public SolicitarAmizadeResponse solicitarAmizade(SolicitarAmizadeRequest request) {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        Usuario amigo = buscarUsuarioService.porId(request.getAmigoId());

        validaUsuarioDiferenteAmigoValidator.validar(usuarioAutenticado.getId(), amigo.getId());
        validaSituacaoAmizadeService.validarSemAmizade(usuarioAutenticado, amigo);

        Amizade amizade = toEntity(usuarioAutenticado, amigo);
        amizade.setSituacao(SOLICITADA);

        amizadeRepository.save(amizade);

        return toResponse(amizade);
    }
}

