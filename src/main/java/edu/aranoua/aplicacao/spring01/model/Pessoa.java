package edu.aranoua.aplicacao.spring01.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true, unique = true,length = 11)
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = true)
    private int idade;
    @JsonIgnore
    @ManyToOne
    private Cidade cidade;
}