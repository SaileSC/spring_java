package dao;

import model.Cidade;
import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CidadeDAO {
    EntityManager em = null;

    public CidadeDAO(EntityManager em){
        this.em = em;
    }

    public void inserir(Cidade cidade){
        EntityTransaction transaction = this.em.getTransaction();

        try{
            transaction.begin();
            this.em.persist(cidade);
            transaction.commit();
        } catch (Exception e) {
            if(transaction.isActive()){
                transaction.rollback();
            }

            throw new RuntimeException(e);
        }
    }

    public Pessoa consultar(long id){
        try{
            return this.em.find(Pessoa.class,id );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
