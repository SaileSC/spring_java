package edu.aranoua.aplicacao.spring01.dto.estado;


import edu.aranoua.aplicacao.spring01.model.Estado;
import edu.aranoua.aplicacao.spring01.repository.PaisRespository;
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
    private String pais;

    public Estado getObject(PaisRespository repository){
        Estado estado = new Estado();
        estado.setPais(repository.findByNome(this.pais));
        estado.setNome(this.nome);
        estado.setSigla(this.sigla);
        return estado;
    }
};
