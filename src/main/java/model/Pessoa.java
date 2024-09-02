package model;

import javax.persistence.*;

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
    public Pessoa() {
    }

    public Pessoa(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

    public void toText(){
        System.out.println("ID: " + getId());
        System.out.println("NOME: " + this.nome);
        System.out.println("IDADE: " + this.idade);
        System.out.println("CPF: " + this.cpf);
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
}
