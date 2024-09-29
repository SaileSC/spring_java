package edu.aranoua.aplicacao.spring01.service;


import edu.aranoua.aplicacao.spring01.model.Estado;
import edu.aranoua.aplicacao.spring01.repository.EstadoRespository;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {
    @Autowired
    EstadoRespository respositoryEstado;

    public List<Estado> list(){
        return respositoryEstado.findAll();
    }

    public Estado read(String nome){
        return respositoryEstado.findByNome(nome).orElseThrow(() ->
                new ObjectnotFoundException("Estado não encontrado NOME:" + nome));
    }

    public Estado create(Estado estado){
        try {
            return respositoryEstado.save(estado);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Estado update(String nome, Estado body){
        Estado estado = respositoryEstado.findByNome(nome).orElseThrow(() ->
                new ObjectnotFoundException("Estado não encontrado NOME::" + body.getNome()));
        try{
            estado.setNome(body.getNome());
            estado.setSigla(body.getSigla());
            return respositoryEstado.save(estado);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String nome){
        try{
            Estado estado = respositoryEstado.findByNome(nome).orElseThrow(() ->
                    new ObjectnotFoundException("Estado não encontrado NOME:" + nome));

            respositoryEstado.delete(estado);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
