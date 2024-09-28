package edu.aranoua.aplicacao.spring01.model.cidade;

import edu.aranoua.aplicacao.spring01.model.estado.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, length = 100)
    private String nome;
    //@ManyToOne
    //private Estado estado;
    // @OneToMany(mappedBy = "cidade")
    //private List<Pessoa> pessoas = new ArrayList<>();
}
