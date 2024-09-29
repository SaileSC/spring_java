package edu.aranoua.aplicacao.spring01.dto.cidade;

import edu.aranoua.aplicacao.spring01.model.Cidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeDTO {
    private  String nome;
    private  String estado;

    public CidadeDTO(Cidade cidade){
        this.nome = cidade.getNome();
        this.estado = cidade.getEstado().getNome();
    }

    public void build(Cidade cidade){
        this.nome = cidade.getNome();
        this.estado = cidade.getEstado().getNome();
    }
};
