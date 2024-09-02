package testes;

import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testandoAlterar {
    public static void main(String[] args){

        EntityManagerFactory fabricEM = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = fabricEM.createEntityManager();

        em.getTransaction().begin();
        Pessoa pessoa =  em.find(Pessoa.class, 1L);
        pessoa.setNome("Ruth");
        pessoa.setIdade(20);
        em.getTransaction().commit();
        em.close();
        fabricEM.close();
    }
}
