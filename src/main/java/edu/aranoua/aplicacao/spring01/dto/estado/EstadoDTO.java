package edu.aranoua.aplicacao.spring01.dto.estado;

import edu.aranoua.aplicacao.spring01.model.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO{
    private String nome;
    private String sigla;

    public EstadoDTO(Estado estado){
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
    }

    public void build(Estado estado){
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
    }
};
