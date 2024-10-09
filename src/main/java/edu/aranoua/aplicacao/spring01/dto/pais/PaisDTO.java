package edu.aranoua.aplicacao.spring01.dto.pais;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaisDTO {
    private String nome;
    private String sigla;
}
