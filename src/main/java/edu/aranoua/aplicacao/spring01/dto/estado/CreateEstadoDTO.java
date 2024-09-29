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
public class CreateEstadoDTO{
    private String nome;
    private String sigla;

    public Estado getObject(){
        Estado estado = new Estado();
        estado.setNome(this.nome);
        estado.setSigla(this.sigla);
        return estado;
    }
};
