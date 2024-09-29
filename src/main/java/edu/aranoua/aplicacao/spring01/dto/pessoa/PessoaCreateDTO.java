package edu.aranoua.aplicacao.spring01.dto.pessoa;

import edu.aranoua.aplicacao.spring01.model.Cidade;
import edu.aranoua.aplicacao.spring01.model.Pessoa;
import edu.aranoua.aplicacao.spring01.service.ServiceCidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaCreateDTO {
    private String nome;
    private int idade;
    private String cpf;
    private  String cidade;


    public Pessoa getObject(ServiceCidade service){
        Pessoa pessoa = new Pessoa();
        pessoa.setIdade(this.idade);
        pessoa.setNome(this.nome);
        pessoa.setCpf(this.cpf);
        Cidade cidade = service.readByNome(this.cidade);
        pessoa.setCidade(cidade);
        return pessoa;
    }
};