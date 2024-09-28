package edu.aranoua.aplicacao.spring01.controller;


import edu.aranoua.aplicacao.spring01.model.cidade.Cidade;
import edu.aranoua.aplicacao.spring01.model.cidade.CidadeDTO;
import edu.aranoua.aplicacao.spring01.repository.CidadeRepository;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    CidadeRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cidade>> list(){
        List<Cidade> cidades = repository.findAll();
        if(cidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(cidades);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> cidade(@PathVariable long id){
        Cidade cidade = repository.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("Cidade ID: " + id));
        return ResponseEntity.ok().body(cidade);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> create(@RequestBody CidadeDTO body){
        try{
            Cidade nCidade = new Cidade();
            BeanUtils.copyProperties(body, nCidade);
            Cidade createCidade = repository.save(nCidade);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createCidade.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(createCidade);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> update(@PathVariable long id,
                                         @RequestBody CidadeDTO body){
        try{
            Cidade cidade = repository.findById(id).orElseThrow(() ->
                    new ObjectnotFoundException("Cidade não encontrada ID: " + id));

            BeanUtils.copyProperties(body, cidade);
            Cidade updateCidade = repository.save(cidade);
            return ResponseEntity.ok().body(updateCidade);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        Cidade cidade = repository.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("Cidade não encontrada ID: " + id));
        try{
            repository.delete(cidade);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}