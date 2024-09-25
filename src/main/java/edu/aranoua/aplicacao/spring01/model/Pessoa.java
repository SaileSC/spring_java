package edu.aranoua.aplicacao.spring01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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
    @ManyToOne
    private Cidade cidade;

    public Pessoa() {
    }

    public Pessoa(String cpf, String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
        cidade.addPessoa(this);
    }

    @Override
    public String toString(){
        return "Pessoa{ "+
                "id:" + this.id +
                ", cpf:" + this.cpf +
                ", nome:" + this.nome +
                ", idade:" + this.idade +
                ", cidade:" + this.cidade +
                " }";
    }
}
