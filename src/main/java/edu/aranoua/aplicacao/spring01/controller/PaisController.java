package edu.aranoua.aplicacao.spring01.controller;


import edu.aranoua.aplicacao.spring01.dto.pais.PaisCreateDTO;
import edu.aranoua.aplicacao.spring01.dto.pais.PaisDTO;
import edu.aranoua.aplicacao.spring01.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pais")
public class PaisController {
    @Autowired
    PaisService paisService;

    @GetMapping
    public ResponseEntity<List<PaisDTO>> list(){
        return ResponseEntity.ok().body(paisService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> read(@PathVariable long id){
        PaisDTO pais = paisService.read(id);
        return ResponseEntity.ok().body(pais);
    }

    @PostMapping
    public ResponseEntity<PaisDTO> create(@RequestBody PaisCreateDTO body){
        PaisDTO pais = paisService.create(body);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pais.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisDTO> update(@PathVariable long id, @RequestBody PaisCreateDTO body){
        return ResponseEntity.ok().body(paisService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        paisService.delete(id);
        return ResponseEntity.ok().build();
    }
}
