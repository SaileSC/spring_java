package testes;

import dao.PessoaDAO;
import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class testaPessoaDao {
    public static void main (String[] args){
        Pessoa pessoa = new Pessoa("Juliana", 16, "98798798765");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = emf.createEntityManager();

        PessoaDAO pessoaDAO = new PessoaDAO(em);

        //pessoaDAO.inserir(pessoa);
        Pessoa p = pessoaDAO.consultar(2L);
        System.out.println(p.getNome());

        em.close();
        emf.close();
    }
}
