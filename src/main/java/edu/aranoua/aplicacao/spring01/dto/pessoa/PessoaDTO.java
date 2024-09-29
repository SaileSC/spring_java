package edu.aranoua.aplicacao.spring01.dto.pessoa;

import edu.aranoua.aplicacao.spring01.model.Cidade;
import edu.aranoua.aplicacao.spring01.model.Pessoa;
import edu.aranoua.aplicacao.spring01.service.ServiceCidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
    private long id;
    private String nome;
    private int idade;
    private String cpf;
    private String cidade;


    public PessoaDTO(Pessoa pessoa){
        this.id = pessoa.getId();
        this.cpf = pessoa.getCpf();
        this.nome = pessoa.getNome();
        this.idade = pessoa.getIdade();
        this.cidade = pessoa.getCidade().getNome();
    }

    public void build(Pessoa pessoa){
        this.id = pessoa.getId();
        this.cpf = pessoa.getCpf();
        this.nome = pessoa.getNome();
        this.idade = pessoa.getIdade();
        this.cidade = pessoa.getCidade().getNome();
    }
};