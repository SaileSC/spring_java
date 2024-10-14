package edu.aranoua.aplicacao.spring01.service;


import edu.aranoua.aplicacao.spring01.dto.pessoa.PessoaCreateDTO;
import edu.aranoua.aplicacao.spring01.dto.pessoa.PessoaDTO;
import edu.aranoua.aplicacao.spring01.model.Cidade;
import edu.aranoua.aplicacao.spring01.model.Pessoa;
import edu.aranoua.aplicacao.spring01.repository.CidadeRepository;
import edu.aranoua.aplicacao.spring01.repository.PessoaRepository;
import edu.aranoua.aplicacao.spring01.service.exception.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicePessoa {
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    CidadeRepository cidadeRepository;

    public List<PessoaDTO> list(){
        return pessoaRepository.findAll()
                .stream()
                .map(PessoaDTO::new)
                .toList();
    }

    public PessoaDTO read(long id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.map(PessoaDTO::new).orElseThrow(() ->
                new ObjectnotFoundException(("Pessoa não encontrado ID:" + id)));
    }

    public PessoaDTO create(PessoaCreateDTO body){
        try {
            return new PessoaDTO(pessoaRepository.save(body.getObject(cidadeRepository)));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public PessoaDTO upadte(long id, PessoaCreateDTO body){
        try {
            Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() ->
                    new ObjectnotFoundException("Pesssoa não encontrada ID:" + id));

            Optional<Cidade> cidade = cidadeRepository.findByNome(body.getCidade());

            if(cidade.isPresent()){
                pessoa.setNome(body.getNome());
                pessoa.setEmail(body.getEmail());
                pessoa.setTelefone(body.getTelefone());
                pessoa.setCidade(cidade.get());
                return new PessoaDTO(pessoaRepository.save(pessoa));
            }
            throw new ObjectnotFoundException("Ciade não encontrada NOME:" + body.getCidade());
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