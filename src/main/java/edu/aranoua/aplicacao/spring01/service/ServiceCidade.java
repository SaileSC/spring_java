package edu.aranoua.aplicacao.spring01.service;

import edu.aranoua.aplicacao.spring01.dto.cidade.CreateCidadeDTO;
import edu.aranoua.aplicacao.spring01.model.Cidade;
import edu.aranoua.aplicacao.spring01.repository.CidadeRepository;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCidade {
    @Autowired
    CidadeRepository cidadeRepository;

    public List<Cidade> list(){
        return cidadeRepository.findAll();
    }


    public Cidade read(long id){
        return cidadeRepository.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("Cidade não encotrada ID:" + id)
        );
    }

    public Cidade readByNome(String nome){
        return cidadeRepository.findByNome(nome).orElseThrow(() ->
                new ObjectnotFoundException("Cidade não encotrada Nome:" + nome)
        );
    }

    public Cidade create(Cidade body){
        try {
            return cidadeRepository.save(body);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public Cidade update(long id, Cidade body){
        try {
            Cidade cidade = cidadeRepository.findById(id).orElseThrow(() ->
                    new ObjectnotFoundException("Cidade não encotrada ID:" + body.getId())
            );

            cidade.setNome(body.getNome());
            cidade.setEstado(body.getEstado());

            return cidadeRepository.save(cidade);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(long id){
        try {
            Cidade cidade = cidadeRepository.findById(id).orElseThrow(() ->
                    new ObjectnotFoundException("Cidade não encotrada ID: " + id));
            cidadeRepository.delete(cidade);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
