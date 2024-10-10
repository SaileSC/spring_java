package edu.aranoua.aplicacao.spring01.service;

import edu.aranoua.aplicacao.spring01.dto.cidade.CidadeDTO;
import edu.aranoua.aplicacao.spring01.dto.cidade.CreateCidadeDTO;
import edu.aranoua.aplicacao.spring01.model.Cidade;
import edu.aranoua.aplicacao.spring01.repository.CidadeRepository;
import edu.aranoua.aplicacao.spring01.repository.EstadoRespository;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCidade {
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRespository estadoRespository;

    public List<CidadeDTO> list(){
        return cidadeRepository.findAll()
                .stream()
                .map(CidadeDTO::new)
                .toList();
    }


    public CidadeDTO read(long id){
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        return cidade.map(CidadeDTO::new).orElseThrow(() ->
                new ObjectnotFoundException("Cidade não encontrada ID:" + id));
    }

    public CidadeDTO create(CreateCidadeDTO body){
        try {
            return new CidadeDTO(cidadeRepository.save(body.getObject(estadoRespository)));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public CidadeDTO update(long id, CreateCidadeDTO body){
        try {
            Cidade cidade = cidadeRepository.findById(id).orElseThrow(() ->
                    new ObjectnotFoundException("Cidade não encotrada ID:" + id)
            );
            cidade.setNome(body.getNome());
            cidade.setEstado(body.getObject(estadoRespository).getEstado());
            return new CidadeDTO(cidadeRepository.save(cidade));
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
