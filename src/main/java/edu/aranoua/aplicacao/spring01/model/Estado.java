package edu.aranoua.aplicacao.spring01.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
     @ManyToOne
     private Pais pais;
}

