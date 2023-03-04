package br.com.cwi.api.controller;

import br.com.cwi.api.controller.request.SolicitarAmizadeRequest;
import br.com.cwi.api.controller.response.IniciarAmizadeResponse;
import br.com.cwi.api.controller.response.SolicitarAmizadeResponse;
import br.com.cwi.api.service.DesfazerAmizadeService;
import br.com.cwi.api.service.IniciarAmizadeService;
import br.com.cwi.api.service.SolicitarAmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/amizades")
@CrossOrigin(origins = "*" )
public class AmizadeController {

    @Autowired
    private SolicitarAmizadeService solicitarAmizadeService;

    @Autowired
    private IniciarAmizadeService iniciarAmizadeService;

    @Autowired
    private DesfazerAmizadeService desfazerAmizadeService;

    @PostMapping("/solicitar")
    public SolicitarAmizadeResponse solicitarAmizade(@Valid @RequestBody SolicitarAmizadeRequest request){
    return solicitarAmizadeService.solicitarAmizade(request);
    }

    @PutMapping("/{id}/iniciar")
    public IniciarAmizadeResponse iniciarAmizade(@PathVariable Long id){
        return iniciarAmizadeService.iniciarAmizade(id);
    }

    @DeleteMapping("/{id}/desfazer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desfazerAmizade(@PathVariable Long id){
        desfazerAmizadeService.desfazerAmizade(id);
    }
}
