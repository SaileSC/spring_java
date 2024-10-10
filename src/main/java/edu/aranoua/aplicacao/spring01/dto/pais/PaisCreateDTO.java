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
public class PaisCreateDTO {
    private String nome;
    private String sigla;

    public Pais build(){
        Pais pais = new Pais();
        pais.setSigla(this.sigla);
        pais.setNome(this.nome);
        return pais;
    }
}
