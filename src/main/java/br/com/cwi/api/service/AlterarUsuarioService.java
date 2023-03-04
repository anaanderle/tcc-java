package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.AlterarUsuarioRequest;
import br.com.cwi.api.controller.response.IdResponse;
import br.com.cwi.api.mapper.IdResponseMapper;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import br.com.cwi.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.api.mapper.IdResponseMapper.*;

@Service
public class AlterarUsuarioService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public IdResponse alterar(AlterarUsuarioRequest request) {

        Usuario usuario = usuarioAutenticadoService.get();

        usuario.setNome(request.getNome());
        usuario.setApelido(request.getApelido());
        usuario.setImagemUrl(request.getImagemUrl());

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }
}
