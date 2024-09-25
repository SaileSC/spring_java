package edu.aranoua.aplicacao.spring01.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true, length = 100)
    private String nome;
    @ManyToOne
    private Estado estado;
    @OneToMany(mappedBy = "cidade")
    private List<Pessoa> pessoas = new ArrayList<Pessoa>();

    public Cidade() {
    }

    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public Cidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        estado.addCidade(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void addPessoa(Pessoa pessoa){
        if(!this.pessoas.contains(pessoa)){
            this.pessoas.add(pessoa);
            pessoa.setCidade(this);
        }

    }

    @Override
    public String toString(){
        return "Cidade { "+
                "id:"+this.id+
                ", nome:"+this.nome+
                ", "+this.estado.toString()+
                " }";
    }
}
