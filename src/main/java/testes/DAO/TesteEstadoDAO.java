package testes.DAO;

import dao.EstadoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteEstadoDAO {
    public static void main(String[] arg){
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("SistemaPU");
        EntityManager em = emf.createEntityManager();
        EstadoDAO estado = new EstadoDAO(em);

        System.out.print(estado.consutar(1L));
    }

}
