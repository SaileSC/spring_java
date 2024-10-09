package edu.aranoua.aplicacao.spring01.service;

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


    public List<Pais> list(){
        return paisRespository.findAll();
    }


    public Pais read(long id){
        return paisRespository.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("Pais não encontrado ID:" + id));
    }

    public Pais create(Pais body){
        return paisRespository.save(body);
    }

    public Pais update(long id, Pais body){
        Pais pais = paisRespository.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("pais não encontrado ID:" + id));
        try{
            pais.setNome(body.getNome());
            pais.setSigla(body.getSigla());

            return paisRespository.save(pais);
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
