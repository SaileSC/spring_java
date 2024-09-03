package testes;

import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class testandoInserirCidade {
    public static void main(String[] args){

        EntityManagerFactory fabricEM = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = fabricEM.createEntityManager();

        Pessoa pessoa = new Pessoa("12316312334", "Ruth", 12);

        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
            em.persist(pessoa);
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive()){
                transaction.rollback();
            }
            
            throw new RuntimeException(e);
        }finally {
            em.close();
            fabricEM.close();
        }
    }
}
