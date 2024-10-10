package edu.aranoua.aplicacao.spring01.dto.pessoa;

import edu.aranoua.aplicacao.spring01.model.Cidade;
import edu.aranoua.aplicacao.spring01.model.Pessoa;
import edu.aranoua.aplicacao.spring01.repository.CidadeRepository;
import edu.aranoua.aplicacao.spring01.service.ServiceCidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaCreateDTO {
    private String nome;
    private int idade;
    private String cpf;
    private  String cidade;


    public Pessoa getObject(CidadeRepository repository){
        Pessoa pessoa = new Pessoa();
        Optional<Cidade> cidade = repository.findByNome(this.cidade);
        if(cidade.isPresent()){
            pessoa.setIdade(this.idade);
            pessoa.setNome(this.nome);
            pessoa.setCpf(this.cpf);
            pessoa.setCidade(cidade.get());
            return pessoa;
        }
        return pessoa;
    }
};