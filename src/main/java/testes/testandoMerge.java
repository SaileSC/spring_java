package testes;

import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testandoMerge {
    public static void main(String[] args){
        EntityManagerFactory fabricEM = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = fabricEM.createEntityManager();

        em.getTransaction().begin();
        Pessoa pessoa =  new Pessoa();

        pessoa.setId(1L);
        pessoa.setNome("Nome alterado antes do merge");
        pessoa.setCpf("234345540'32");
        pessoa.setIdade(34);
        em.merge(pessoa);

        em.getTransaction().commit();

        pessoa.toText();
        em.close();
        fabricEM.close();
    }
}
