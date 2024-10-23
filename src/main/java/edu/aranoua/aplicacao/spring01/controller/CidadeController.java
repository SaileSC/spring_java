package edu.aranoua.aplicacao.spring01.controller;


import edu.aranoua.aplicacao.spring01.model.Cidade;
import edu.aranoua.aplicacao.spring01.dto.cidade.CreateCidadeDTO;
import edu.aranoua.aplicacao.spring01.dto.cidade.CidadeDTO;
import edu.aranoua.aplicacao.spring01.repository.CidadeRepository;
import edu.aranoua.aplicacao.spring01.repository.EstadoRespository;
import edu.aranoua.aplicacao.spring01.service.EstadoService;
import edu.aranoua.aplicacao.spring01.service.ServiceCidade;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {
    @Autowired
    ServiceCidade serviceCidade;
    @Autowired
    EstadoService estadoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CidadeDTO>> list(){
        return ResponseEntity.ok().body(serviceCidade.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> read(@PathVariable long id){
        return ResponseEntity.ok().body(serviceCidade.read(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<CidadeDTO>> create(@RequestBody CreateCidadeDTO body){
        CidadeDTO cidade = serviceCidade.create(body);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cidade.getId())
                .toUri();

        Link list = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CidadeController.class).list()
        ).withRel("list");

        Link read = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CidadeController.class).read(cidade.getId())
        ).withRel("read");

        Link update = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CidadeController.class).update(cidade.getId(), body)
        ).withRel("update");

        Link delete = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(CidadeController.class).delete(cidade.getId())
        ).withRel("delete");

        EntityModel<CidadeDTO> paisModel= EntityModel.of(cidade)
                .add(list, read, update, delete);
        return ResponseEntity.created(uri).body(paisModel);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> update(@PathVariable long id,
                                         @RequestBody CreateCidadeDTO body){
       return ResponseEntity.ok().body(serviceCidade.update(id, body));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable long id){
        serviceCidade.delete(id);
        return ResponseEntity.ok().build();
    }
}