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
        List<Cidade> cidades = serviceCidade.list();

        List<CidadeDTO> cidadesDTO = cidades.stream()
                .map(CidadeDTO::new)
                .toList();

        return ResponseEntity.ok().body(cidadesDTO);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> cidade(@PathVariable long id){
        Cidade cidade = serviceCidade.read(id);
        CidadeDTO cidadeDTO = new CidadeDTO(cidade);
        return ResponseEntity.ok().body(cidadeDTO);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> create(@RequestBody CreateCidadeDTO body){
        try{
            Cidade cidade = serviceCidade.create(body.getObject(estadoService));

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(cidade.getId())
                    .toUri();

            CidadeDTO cidadeDTO = new CidadeDTO(cidade);
            return ResponseEntity.created(uri).body(cidadeDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> update(@PathVariable long id,
                                         @RequestBody CreateCidadeDTO body){
        try{
            Cidade cidade = serviceCidade.update(id, body.getObject(estadoService));
            CidadeDTO cidadeDTO = new CidadeDTO(cidade);
            return ResponseEntity.ok().body(cidadeDTO);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try{
            serviceCidade.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}