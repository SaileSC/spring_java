package edu.aranoua.aplicacao.spring01.dto.cidade;

import edu.aranoua.aplicacao.spring01.model.Cidade;
import edu.aranoua.aplicacao.spring01.model.Estado;
import edu.aranoua.aplicacao.spring01.repository.EstadoRespository;
import edu.aranoua.aplicacao.spring01.service.EstadoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCidadeDTO {
    private  String nome;
    private  String estado;

    public Cidade getObject(EstadoRespository respository){
        Cidade cidade = new Cidade();
        Optional<Estado> estado = respository.findByNome(this.estado);
        if(estado.isPresent()){
            cidade.setNome(this.nome);
            cidade.setEstado(estado.get());
            return cidade;
        }
        return cidade;
    }
};
