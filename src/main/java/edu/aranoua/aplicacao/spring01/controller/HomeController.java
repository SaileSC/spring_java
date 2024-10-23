package edu.aranoua.aplicacao.spring01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String init(){
        return "<div style='background-color:green; font-size:30; width: 400px'" +
                "> Recursos acessiveis: <br> Paises : /api/pais " +
                "<br> Estados: /api/estado <br> Cidade: /api/cidade <br>  Pessoa: /api/pessoa</div>";
    }
}
