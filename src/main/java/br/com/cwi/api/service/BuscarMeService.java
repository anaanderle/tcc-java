package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.BuscarMeResponse;
import br.com.cwi.api.controller.response.UsuarioResumidoResponse;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.cwi.api.mapper.UsuarioMeMapper.toResponse;

@Service
public class BuscarMeService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public BuscarMeResponse buscar() {

        Usuario usuarioAutenticado = usuarioAutenticadoService.get();

        return toResponse(usuarioAutenticado);
    }
}
