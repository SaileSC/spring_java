package testes;

import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testandoDeletar {
    public static void main(String[] args){

        EntityManagerFactory fabricEM = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = fabricEM.createEntityManager();

        em.getTransaction().begin();
        Pessoa pessoa =  em.find(Pessoa.class, 1L);
        em.remove(pessoa);
        em.getTransaction().commit();
        em.close();
        fabricEM.close();
    }
}
