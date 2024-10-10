package edu.aranoua.aplicacao.spring01.service;

import edu.aranoua.aplicacao.spring01.dto.pais.PaisCreateDTO;
import edu.aranoua.aplicacao.spring01.dto.pais.PaisDTO;
import edu.aranoua.aplicacao.spring01.model.Pais;
import edu.aranoua.aplicacao.spring01.repository.PaisRespository;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {
    @Autowired
    PaisRespository paisRespository;


    public List<PaisDTO> list(){
        return paisRespository.findAll()
                .stream()
                .map(PaisDTO::new)
                .toList();
    }


    public PaisDTO read(long id){
        return paisRespository.findById(id)
                .map(PaisDTO::new).orElseThrow(() ->
                        new ObjectnotFoundException("pais não encontrado ID:" + id));
    }

    public PaisDTO create(PaisCreateDTO body){
        return new PaisDTO(paisRespository.save(body.build()));
    }

    public PaisDTO update(long id, PaisCreateDTO body){
        Pais pais = paisRespository.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("pais não encontrado ID:" + id));
        try{
            pais.setNome(body.getNome());
            pais.setSigla(body.getSigla());

            return new PaisDTO(paisRespository.save(pais));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id){
        Pais pais = paisRespository.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("país não encotrado ID:" + id));
        paisRespository.delete(pais);
    }
}
