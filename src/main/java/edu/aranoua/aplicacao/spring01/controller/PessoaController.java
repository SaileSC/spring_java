package edu.aranoua.aplicacao.spring01.controller;


import com.sun.net.httpserver.HttpsServer;
import edu.aranoua.aplicacao.spring01.dto.pessoa.PessoaDTO;
import edu.aranoua.aplicacao.spring01.model.Pessoa;
import edu.aranoua.aplicacao.spring01.dto.pessoa.PessoaCreateDTO;
import edu.aranoua.aplicacao.spring01.service.ServiceCidade;
import edu.aranoua.aplicacao.spring01.service.ServicePessoa;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    ServicePessoa servicePessoa;
    @Autowired
    ServiceCidade serviceCidade;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaDTO>> list(){
        return ResponseEntity.ok().body(servicePessoa.list());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<PessoaDTO> read(@PathVariable long id){
        return ResponseEntity.ok().body(servicePessoa.read(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<PessoaDTO>> create(@RequestBody PessoaCreateDTO body, UriComponentsBuilder uriBuilder){
            PessoaDTO pessoa = servicePessoa.create(body);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(pessoa.getId())
                    .toUri();

            Link list = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(PessoaController.class).list()
            ).withRel("list");

            Link read = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(PessoaController.class).read(pessoa.getId())
            ).withRel("read");

            Link update = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(PessoaController.class).update(pessoa.getId(), body)
            ).withRel("update");

            Link delete = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(PessoaController.class).delete(pessoa.getId())
            ).withRel("delete");

            EntityModel<PessoaDTO> pessoaModel = EntityModel.of(pessoa)
                    .add(list, read, update, delete);
            return ResponseEntity.created(uri).body(pessoaModel);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<PessoaDTO> update(@PathVariable long id,
                                          @RequestBody PessoaCreateDTO body){
        return ResponseEntity.ok().body(servicePessoa.upadte(id, body));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable long id){
        try{
            servicePessoa.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
