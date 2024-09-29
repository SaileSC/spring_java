package edu.aranoua.aplicacao.spring01.controller;


import edu.aranoua.aplicacao.spring01.dto.pessoa.PessoaDTO;
import edu.aranoua.aplicacao.spring01.model.Pessoa;
import edu.aranoua.aplicacao.spring01.dto.pessoa.PessoaCreateDTO;
import edu.aranoua.aplicacao.spring01.service.ServiceCidade;
import edu.aranoua.aplicacao.spring01.service.ServicePessoa;
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
    ServicePessoa servicePessoa;
    @Autowired
    ServiceCidade serviceCidade;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PessoaDTO>> list(){
        List<Pessoa> pessoas = servicePessoa.list();
        if(pessoas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<PessoaDTO> pessoasDTO = pessoas.stream()
                .map(PessoaDTO::new)
                .toList();

        return ResponseEntity.ok().body(pessoasDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<PessoaDTO> read(@PathVariable long id){
        Pessoa pessoa = servicePessoa.read(id);
        PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
        return ResponseEntity.ok().body(pessoaDTO);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PessoaDTO> create(@RequestBody PessoaCreateDTO body){
        try{
            Pessoa pessoa = servicePessoa.create(body.getObject(serviceCidade));
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(pessoa.getId())
                    .toUri();
            PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
            return ResponseEntity.created(uri).body(pessoaDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<PessoaDTO> update(@PathVariable long id,
                                          @RequestBody PessoaCreateDTO body){
        try {
            Pessoa pessoa = servicePessoa.upadte(id, body.getObject(serviceCidade));
            PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
            return ResponseEntity.ok().body(pessoaDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try{
            servicePessoa.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
