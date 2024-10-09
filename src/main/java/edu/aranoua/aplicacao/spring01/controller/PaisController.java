package edu.aranoua.aplicacao.spring01.controller;


import edu.aranoua.aplicacao.spring01.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PaisController {
    @Autowired
    PaisService paisService;


}
