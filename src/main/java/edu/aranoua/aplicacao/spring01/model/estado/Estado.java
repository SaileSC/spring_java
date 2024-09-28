package edu.aranoua.aplicacao.spring01.model.estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "estado")
public class Estado {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     @Column(nullable = false, unique = true)
     private String nome;
     @Column(nullable = false, unique = true)
     private String sigla;
     //@OneToMany(mappedBy = "estado")
     //private List<Cidade> cidades = new ArrayList<Cidade>();
}

