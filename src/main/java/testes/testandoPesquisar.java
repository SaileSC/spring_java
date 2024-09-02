package testes;

import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class testandoPesquisar {
    public static void main(String[] args){
        EntityManagerFactory fabricEM = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = fabricEM.createEntityManager();
        Query query = em.createQuery("select p from Pessoa p where p.nome LIKE :pNome");
        query.setParameter("pNome", "Lara");
        List<Pessoa> resultList= query.getResultList();

        for(Pessoa pessoa : resultList) {
            System.out.println(pessoa.getNome());
            System.out.println(pessoa.getIdade());
        }


        em.close();
        fabricEM.close();
    }
}
