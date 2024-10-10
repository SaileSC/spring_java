package edu.aranoua.aplicacao.spring01.service;


import edu.aranoua.aplicacao.spring01.dto.estado.CreateEstadoDTO;
import edu.aranoua.aplicacao.spring01.dto.estado.EstadoDTO;
import edu.aranoua.aplicacao.spring01.model.Estado;
import edu.aranoua.aplicacao.spring01.repository.EstadoRespository;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {
    @Autowired
    EstadoRespository respositoryEstado;

    public List<EstadoDTO> list(){
        return respositoryEstado.findAll()
                .stream()
                .map(EstadoDTO::new)
                .toList();
    }

    public EstadoDTO read(long id){
        return respositoryEstado.findById(id)
                .map(EstadoDTO::new).orElseThrow(() ->
                new ObjectnotFoundException("Estado ID:" + id +" não encontrado"));
    }

    public EstadoDTO create(CreateEstadoDTO estado){
        try {
            return new EstadoDTO(respositoryEstado.save(estado.getObject()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public EstadoDTO update(long id, CreateEstadoDTO body){
        Estado estado = respositoryEstado.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("Estado ID:" + id +" não encontrado")
        );

        try{
            estado.setNome(body.getNome());
            estado.setSigla(body.getSigla());
            return new EstadoDTO(respositoryEstado.save(estado));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id){
        Estado estado = respositoryEstado.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("Estado ID:" + id +" não encontrado")
        );

        try{
            respositoryEstado.delete(estado);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
