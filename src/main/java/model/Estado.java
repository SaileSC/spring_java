package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false, unique = true)
    private String sigla;
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades = new ArrayList<Cidade>();

    public Estado() {
    }

    public Estado(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public void addCidade(Cidade cidade){
        if(!this.cidades.contains(cidade)){
            this.cidades.add(cidade);
            cidade.setEstado(this);
        }
    }


    public String getCidadesString() {
        return this.cidades.stream()
                .map(Cidade::getNome)
                .collect(Collectors.joining(", "));
    }

    @Override
    public String toString(){
        return "Estado { id:" + this.id + ", nome:" + this.nome +
                ", sigla:" + this.sigla +", Cidades: ["+ this.getCidadesString() +"] }";
    }
}
