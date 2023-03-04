package br.com.cwi.api.service;

import br.com.cwi.api.controller.request.IncluirUsuarioRequest;
import br.com.cwi.api.controller.response.IncluirUsuarioResponse;
import br.com.cwi.api.security.domain.Permissao;
import br.com.cwi.api.security.domain.Usuario;
import br.com.cwi.api.mapper.IncluirUsuarioMapper;
import br.com.cwi.api.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.cwi.api.mapper.IncluirUsuarioMapper.*;

@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidaEmailUnicoService validaEmailUnicoService;

    public IncluirUsuarioResponse incluir(IncluirUsuarioRequest request) {

        validaEmailUnicoService.validar(request.getEmail());

        Usuario usuario = toEntity(request);
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setAtivo(true);

        request.getPermissoes()
                .forEach(permissao -> usuario.adicionarPermissao(Permissao.builder().nome(permissao).build()));

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }
}
