package edu.aranoua.aplicacao.spring01.controller;


import edu.aranoua.aplicacao.spring01.dto.cidade.CidadeDTO;
import edu.aranoua.aplicacao.spring01.dto.pais.PaisCreateDTO;
import edu.aranoua.aplicacao.spring01.dto.pais.PaisDTO;
import edu.aranoua.aplicacao.spring01.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pais")
public class PaisController {
    @Autowired
    PaisService paisService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PaisDTO>> list(){
        return ResponseEntity.ok().body(paisService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisDTO> read(@PathVariable long id){
        PaisDTO pais = paisService.read(id);
        return ResponseEntity.ok().body(pais);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<PaisDTO>> create(@RequestBody PaisCreateDTO body){
        PaisDTO pais = paisService.create(body);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pais.getId())
                .toUri();

        Link list = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(PaisController.class).list()
        ).withRel("list");

        Link read = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(PaisController.class).read(pais.getId())
        ).withRel("read");

        Link update = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(PaisController.class).update(pais.getId(), body)
        ).withRel("update");

        Link delete = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(PaisController.class).delete(pais.getId())
        ).withRel("delete");

        EntityModel<PaisDTO> paisModel = EntityModel.of(pais)
                .add(list, read, update, delete);

        return ResponseEntity.created(uri).body(paisModel);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaisDTO> update(@PathVariable long id, @RequestBody PaisCreateDTO body){
        return ResponseEntity.ok().body(paisService.update(id, body));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable long id){
        paisService.delete(id);
        return ResponseEntity.ok().build();
    }
}
