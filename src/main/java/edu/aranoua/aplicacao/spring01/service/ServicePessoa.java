package edu.aranoua.aplicacao.spring01.service;


import edu.aranoua.aplicacao.spring01.model.Pessoa;
import edu.aranoua.aplicacao.spring01.repository.PessoaRepository;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePessoa {

    @Autowired
    PessoaRepository pessoaRepository;

    public List<Pessoa> list(){
        return pessoaRepository.findAll();
    }

    public Pessoa read(long id){
        return pessoaRepository.findById(id).orElseThrow(() ->
                new ObjectnotFoundException("Pesssoa não encontrada ID:" + id));
    }

    public Pessoa create(Pessoa body){
        try {
            return pessoaRepository.save(body);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public Pessoa upadte(long id, Pessoa body){
        try {
            Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() ->
                    new ObjectnotFoundException("Pesssoa não encontrada ID:" + body.getId()));

            pessoa.setNome(body.getNome());
            pessoa.setCpf(body.getCpf());
            pessoa.setIdade(body.getIdade());

            return pessoaRepository.save(pessoa);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(long id){
        try {
            Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() ->
                    new ObjectnotFoundException("Pesssoa não encontrada ID:" + id));
            pessoaRepository.delete(pessoa);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}