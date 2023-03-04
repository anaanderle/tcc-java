package br.com.cwi.api.service;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.repository.AmizadeRespository;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import br.com.cwi.api.validator.ValidaUsuarioDesfazendoAmizadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.domain.SituacaoAmizade.ATIVA;

@Service
public class DesfazerAmizadeService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarAmizadeService buscarAmizadeService;

    @Autowired
    private ValidaUsuarioDesfazendoAmizadeValidator validaUsuarioDesfazendoAmizadeValidator;

    @Autowired
    private AmizadeRespository amizadeRespository;

    @Transactional
    public void desfazerAmizade(Long id) {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();
        Amizade amizade = buscarAmizadeService.porSituacao(ATIVA, id);
        validaUsuarioDesfazendoAmizadeValidator.validar(amizade, usuarioAutenticado);

        amizadeRespository.deleteById(amizade.getId());
    }
}
