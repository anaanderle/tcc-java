package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.BuscarUsuariosRequest;
import br.com.cwi.api.controller.response.UsuarioResumidoResponse;
import br.com.cwi.api.mapper.UsuarioResumidoMapper;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


@Service
public class BuscarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResumidoResponse> buscarUsuarios(String request) {

        List<Usuario> usuarios = usuarioRepository
                .findByNomeContainingOrEmailContainingAllIgnoreCase(request, request);

        return usuarios.stream()
                .map(UsuarioResumidoMapper::toResponse)
                .collect(toList());
    }
}
