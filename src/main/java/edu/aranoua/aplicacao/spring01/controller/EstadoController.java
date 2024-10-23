package edu.aranoua.aplicacao.spring01.controller;

import java.util.List;
import java.net.URI;
import java.util.stream.Collectors;

import edu.aranoua.aplicacao.spring01.dto.estado.CreateEstadoDTO;
import edu.aranoua.aplicacao.spring01.dto.estado.EstadoDTO;
import edu.aranoua.aplicacao.spring01.service.EstadoService;
import jakarta.persistence.Entity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok().body(estadoService.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoDTO> read(@PathVariable long id) {
        return ResponseEntity.ok().body(estadoService.read(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<EstadoDTO>> criar(@RequestBody CreateEstadoDTO body) {
        EstadoDTO estado = estadoService.create(body);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(estado.getId())
                .toUri();
        Link list = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(EstadoController.class).list()
        ).withRel("list");

        Link read = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(EstadoController.class).read(estado.getId())
        ).withRel("read");

        Link update = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(EstadoController.class).update(estado.getId(), body)
        ).withRel("update");

        Link delete = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(EstadoController.class).delete(estado.getId())
        ).withRel("delete");

        EntityModel<EstadoDTO> estadoModel = EntityModel.of(estado)
                .add(list, read, update, delete);
        return ResponseEntity.created(uri).body(estadoModel);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoDTO> update(@PathVariable long id, @RequestBody CreateEstadoDTO body) {
            return ResponseEntity.ok().body(estadoService.update(id, body));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable long id){
            estadoService.delete(id);
            return ResponseEntity.ok().build();
    }
    
}
