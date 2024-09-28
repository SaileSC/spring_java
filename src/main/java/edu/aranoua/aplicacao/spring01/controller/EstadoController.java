package edu.aranoua.aplicacao.spring01.controller;

import java.util.List;
import java.util.Optional;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.aranoua.aplicacao.spring01.model.estado.Estado;
import edu.aranoua.aplicacao.spring01.repository.EstadoRespository;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    EstadoRespository respository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list() {
        List<Estado> estados = respository.findAll();
        if(estados.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(estados);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estado> read(@PathVariable long id) {
        Optional<Estado> opEstado = respository.findById(id);
        return opEstado.map(ResponseEntity::ok).orElseThrow(() -> new ObjectnotFoundException("Estado não encontrado ID: " + id));
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> criar(@RequestBody Estado body) {
        try {
            Estado estado = respository.save(body);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(estado.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(estado);
        } catch (RuntimeException e) {
            throw   new RuntimeException(e);
        }
    }



    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> atualizar(@PathVariable long id, @RequestBody Estado body) {
        try {
            Estado estado = respository.findById(id)
                    .orElseThrow(() -> new ObjectnotFoundException("Estado não encontrado, ID:" + id));

            estado.setNome(body.getNome());
            estado.setSigla(body.getSigla());
            Estado altEstado = respository.save(estado);

            return ResponseEntity.ok().body(altEstado);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable long id){
         respository.delete(respository.findById(id).orElseThrow(() ->
         new ObjectnotFoundException("Estado não encontrado, ID:" + id)));
         return ResponseEntity.ok().build();
    }
    
}
