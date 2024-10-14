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
    private long id;
    private String nome;
    private String sigla;
    private String pais;

    public EstadoDTO(Estado estado){
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.sigla = estado.getSigla();
        this.pais = estado.getPais().getNome();
    }
};
