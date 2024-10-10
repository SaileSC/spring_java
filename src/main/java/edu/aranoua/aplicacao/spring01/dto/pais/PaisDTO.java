package edu.aranoua.aplicacao.spring01.dto.pais;

import edu.aranoua.aplicacao.spring01.model.Pais;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaisDTO {
    private long id;
    private String nome;
    private String sigla;

    public PaisDTO(Pais pais){
        this.id = pais.getId();
        this.nome = pais.getNome();
        this.sigla = pais.getSigla();
    }
}
