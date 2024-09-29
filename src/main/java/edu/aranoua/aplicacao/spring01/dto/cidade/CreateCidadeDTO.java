package edu.aranoua.aplicacao.spring01.dto.cidade;

import edu.aranoua.aplicacao.spring01.model.Cidade;
import edu.aranoua.aplicacao.spring01.model.Estado;
import edu.aranoua.aplicacao.spring01.repository.EstadoRespository;
import edu.aranoua.aplicacao.spring01.service.EstadoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCidadeDTO {
    private  String nome;
    private  String estado;

    public Cidade getObject(EstadoService service){
        Cidade cidade = new Cidade();
        cidade.setNome(this.nome);
        Estado estado = service.read(this.estado);
        cidade.setEstado(estado);

        return cidade;
    }
};
