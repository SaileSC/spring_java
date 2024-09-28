package edu.aranoua.aplicacao.spring01.controller;


import edu.aranoua.aplicacao.spring01.model.pessoa.Pessoa;
import edu.aranoua.aplicacao.spring01.model.pessoa.PessoaDTO;
import edu.aranoua.aplicacao.spring01.repository.PessoaRepository;
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
@RequestMapping("/api/pessoa")
public class PessoaController {
    @Autowired
    PessoaRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pessoa>> list(){
        List<Pessoa> pessoas = repository.findAll();
        if(pessoas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(pessoas);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Pessoa> read(@PathVariable long id){
        Pessoa pessoa = repository.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("Pessoa ID: " + id + ", não encontrada"));
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa body){
        try{
            Pessoa pessoa = repository.save(body);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(pessoa.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(pessoa);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Pessoa> update(@PathVariable long id,
                                          @RequestBody PessoaDTO body){

        try {
            Pessoa pessoa = repository.findById(id).orElseThrow(() ->
                    new ObjectnotFoundException("Pessoa não encontrada ID: " + id));

            BeanUtils.copyProperties(body, pessoa);

            Pessoa updatePessoa = repository.save(pessoa);
            return ResponseEntity.ok().body(updatePessoa);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try{
            Pessoa pessoa = repository.findById(id).orElseThrow(() ->
                    new ObjectnotFoundException("Pessoa não encontrada ID: " + id));
            repository.delete(pessoa);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
