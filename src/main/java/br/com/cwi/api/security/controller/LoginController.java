package br.com.cwi.api.security.controller;

import br.com.cwi.api.controller.response.BuscarMeResponse;
import br.com.cwi.api.service.BuscarMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private BuscarMeService buscarUsuarioService;

    @PostMapping
    public BuscarMeResponse login() {
        return buscarUsuarioService.buscar();
    }
}
