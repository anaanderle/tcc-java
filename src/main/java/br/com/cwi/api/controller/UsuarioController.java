package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.AlterarUsuarioRequest;
import br.com.cwi.api.controller.request.BuscarUsuariosRequest;
import br.com.cwi.api.controller.response.*;
import br.com.cwi.api.controller.request.IncluirUsuarioRequest;
import br.com.cwi.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private BuscarMeService buscarMeService;

    @Autowired
    private AlterarUsuarioService alterarUsuarioService;

    @Autowired
    private BuscarUsuariosService buscarUsuariosService;

    @Autowired
    private BuscarAmigosService buscarAmigosService;

    @Autowired
    private BuscarSolicitacoesService buscarSolicitacoesService;

    @Autowired
    private ListarMePostsService listarMePostsService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @PostMapping
    public IncluirUsuarioResponse incluir(@Valid @RequestBody IncluirUsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @PutMapping
    public IdResponse alterar(@Valid @RequestBody AlterarUsuarioRequest request){
        return alterarUsuarioService.alterar(request);
    }

    @GetMapping("/me")
    public BuscarMeResponse buscarMe() {
        return buscarMeService.buscar();
    }

    @GetMapping("/{id}/buscar")
    public UsuarioResumidoResponse buscarUsuario(@PathVariable Long id) {
        return buscarUsuarioService.buscarUsuario(id);
    }

    @GetMapping("/me/posts")
    public Page<PostResponse> listarMePosts(Pageable pageable){
        return listarMePostsService.listar(pageable);
    }
    @GetMapping("/{texto}")
    public List<UsuarioResumidoResponse> buscarUsuarios(@PathVariable String texto){
        return buscarUsuariosService.buscarUsuarios(texto);
    }

    @GetMapping("/amigos")
    public List<AmizadeResumidaResponse> buscarAmigos(){
        return buscarAmigosService.buscarAmigos();
    }

    @GetMapping("/amigos/solicitacoes")
    public List<AmizadeResumidaResponse> buscarSolicitacoes(){
        return buscarSolicitacoesService.buscarSolicitacoes();
    }
}
