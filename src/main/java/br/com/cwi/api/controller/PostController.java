package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.IncluirComentarioPostRequest;
import br.com.cwi.api.controller.request.IncluirPostRequest;
import br.com.cwi.api.controller.response.IdResponse;
import br.com.cwi.api.controller.response.PostResponse;
import br.com.cwi.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IncluirPostService incluirPostService;

    @Autowired
    private AlterarPostService alterarPostService;

    @Autowired
    private CurtirPostService curtirPostService;

    @Autowired
    private DescurtirPostService descurtirPostService;

    @Autowired
    private ListarPostsService listarPostsService;

    @Autowired
    private IncluirComentarioPostService incluirComentarioPostService;

    @PostMapping
    private IdResponse incluir(@Valid @RequestBody IncluirPostRequest request){
        return incluirPostService.incluir(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void alterarPermissao(@PathVariable Long id){
        alterarPostService.alterarPermissao(id);
    }

    @GetMapping("/{id}")
    private List<PostResponse> listar(@PathVariable Long id){
        return listarPostsService.listar(id);
    }

    @PostMapping("/{id}/curtir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void curtirPost(@PathVariable Long id){
        curtirPostService.curtirPost(id);
    }

    @DeleteMapping("/{id}/descurtir")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void descurtirPost(@PathVariable Long id){
        descurtirPostService.descurtirPost(id);
    }

    @PostMapping("/{id}/comentar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void incluirComentario(@PathVariable Long id, @Valid @RequestBody IncluirComentarioPostRequest request){
        incluirComentarioPostService.incluirComentario(id, request);
    }

}
