package edu.aranoua.aplicacao.spring01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/init")
    public String init(){
        return "Testando HomeController - HTML testes";
    }
}
