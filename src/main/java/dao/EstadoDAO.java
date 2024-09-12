package dao;

import model.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EstadoDAO {
    EntityManager em = null;

    public EstadoDAO(EntityManager em) {
        this.em = em;
    }

    public void inserir(Estado estado){
        EntityTransaction transaction = this.em.getTransaction();
        try{
            transaction.begin();
            this.em.persist(estado);
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive()){
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    public Estado consutar(long id){
        try{
            return this.em.find(Estado.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
