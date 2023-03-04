package br.com.cwi.api.service;

import br.com.cwi.api.controller.response.UsuarioResumidoResponse;
import br.com.cwi.api.mapper.UsuarioResumidoMapper;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class BuscarUsuarioService {

    final String ERRO_USUARIO_INEXISTENTE = "Usuario inexistente";
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario porId(Long id){

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(BAD_REQUEST, ERRO_USUARIO_INEXISTENTE)
                );
    }

    public UsuarioResumidoResponse buscarUsuario(Long id) {

        Usuario usuario = porId(id);

        return UsuarioResumidoMapper.toResponse(usuario);
    }
}
