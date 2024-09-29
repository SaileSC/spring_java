package edu.aranoua.aplicacao.spring01.controller;

import java.util.List;
import java.net.URI;
import java.util.stream.Collectors;

import edu.aranoua.aplicacao.spring01.dto.estado.CreateEstadoDTO;
import edu.aranoua.aplicacao.spring01.dto.estado.EstadoDTO;
import edu.aranoua.aplicacao.spring01.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.aranoua.aplicacao.spring01.model.Estado;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    EstadoService estadoService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstadoDTO>> list() {
        try{
            List<Estado> estados = estadoService.list();
            List<EstadoDTO> estadosDTO = estados.stream()
                    .map(EstadoDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok().body(estadosDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping(value = "/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoDTO> read(@PathVariable String nome) {
        try{
            Estado estado = estadoService.read(nome);
            EstadoDTO estadoDTO = new EstadoDTO(estado);
            return ResponseEntity.ok().body(estadoDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoDTO> criar(@RequestBody CreateEstadoDTO body) {
        try {
            Estado estado = estadoService.create(body.getObject());

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{nome}")
                    .buildAndExpand(estado.getNome().toLowerCase())
                    .toUri();

            EstadoDTO estadoDTO = new EstadoDTO(estado);

            return ResponseEntity.created(uri).body(estadoDTO);
        } catch (RuntimeException e) {
            throw   new RuntimeException(e);
        }
    }



    @PutMapping(value = "/{nome}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoDTO> atualizar(@PathVariable String nome, @RequestBody CreateEstadoDTO body) {
        try {
            Estado estado = estadoService.update(nome, body.getObject());

            EstadoDTO estadoDTO = new EstadoDTO(estado);
            return ResponseEntity.ok().body(estadoDTO);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    

    @DeleteMapping(value = "/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable String nome){
         try {
             estadoService.delete(nome);
             return ResponseEntity.ok().build();
         } catch (RuntimeException e) {
             throw new RuntimeException(e);
         }
    }
    
}
