package testes;

import model.Cidade;
import model.Estado;
import model.Pessoa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestePessoaComCidade {
    public static void inserir(){
        EntityManagerFactory fabricaEM = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager em = fabricaEM.createEntityManager();

        Pessoa pessoa = new Pessoa("12345678905","Pessoa com Cidade",31);

        Estado estado = new Estado();
        estado.setNome("Amazonas");
        estado.setSigla("AM");

        Cidade cidade = new Cidade();
        cidade.setNome("Manaus");
        cidade.setEstado(estado);

        pessoa.setCidade(cidade);

        EntityTransaction transacao = em.getTransaction();

        try {
            transacao.begin();

            em.persist(estado);

            em.persist(cidade);

            em.persist(pessoa);

            transacao.commit();

        }catch(Exception e) {

            if(transacao.isActive()) {
                transacao.rollback();
            }
            throw new RuntimeException(e);

        }finally{
            em.close();
            fabricaEM.close();
        }
    }

    public static void inserirComCidadeExistenteNoBD(){

        EntityManagerFactory fabricaEM = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager em = fabricaEM.createEntityManager();

        Pessoa pessoa = new Pessoa("12345678915","Pessoa 02 com Cidade 01",31);

        EntityTransaction transacao = em.getTransaction();

        try {
            transacao.begin();

            Cidade cidade = em.find(Cidade.class,1L);

            pessoa.setCidade(cidade);

            em.persist(pessoa);

            transacao.commit();

        }catch(Exception e) {

            if(transacao.isActive()) {
                transacao.rollback();
            }
            throw new RuntimeException(e);

        }finally{
            em.close();
            fabricaEM.close();
        }
    }

    public static void consultar(){

        EntityManagerFactory fabricaEM = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager em = fabricaEM.createEntityManager();

        try {

            Pessoa pessoa = em.find(Pessoa.class,1L);

            System.out.println(pessoa);

        }catch(Exception e) {

            throw new RuntimeException(e);

        }finally{
            em.close();
            fabricaEM.close();
        }
    }

    public static void inserirPessoaNaCidade(){
        EntityManagerFactory fabricaEM = Persistence.createEntityManagerFactory("SistemaPU");

        EntityManager em = fabricaEM.createEntityManager();

        Pessoa pessoa1 = new Pessoa("12345678903","Pessoa com Cidade1",15);
        Pessoa pessoa2 = new Pessoa("12345678901","Pessoa com Cidade2",32);
        Pessoa pessoa3 = new Pessoa("12345678902","Pessoa com Cidade3",32);

        Estado estado = new Estado();
        estado.setNome("São Paulo");
        estado.setSigla("SP");

        Cidade cidade = new Cidade();
        cidade.setNome("Campinas");
        cidade.setEstado(estado);

        cidade.addPessoa(pessoa1);
        cidade.addPessoa(pessoa2);
        cidade.addPessoa(pessoa3);

        EntityTransaction transacao = em.getTransaction();

        try {
            transacao.begin();

            em.persist(estado);

            em.persist(cidade);

            em.persist(pessoa1);
            em.persist(pessoa2);
            em.persist(pessoa3);

            transacao.commit();

        }catch(Exception e) {

            if(transacao.isActive()) {
                transacao.rollback();
            }
            throw new RuntimeException(e);

        }finally{
            em.close();
            fabricaEM.close();
        }
    }
    public static void inserirCidadesEmEstado(){
        EntityManagerFactory fabricaEM = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = fabricaEM.createEntityManager();

        Cidade cidade1 = new Cidade("Manaus");
        Cidade cidade2 = new Cidade("Manacapuru");
        Cidade cidade3 = new Cidade("Parintis");

        Estado estado = new Estado();
        estado.setNome("Amazonas");
        estado.setSigla("AM");

        estado.addCidade(cidade1);
        estado.addCidade(cidade2);
        estado.addCidade(cidade3);

        EntityTransaction transacao = em.getTransaction();
        try {
            transacao.begin();

            em.persist(estado);
            em.persist(cidade1);
            em.persist(cidade2);
            em.persist(cidade3);

            transacao.commit();

        }catch(Exception e) {

            if(transacao.isActive()) {
                transacao.rollback();
            }
            throw new RuntimeException(e);

        }finally{
            em.close();
            fabricaEM.close();
        }
    }


    public static void consultarEstado(){
        EntityManagerFactory fabricaEM = Persistence.createEntityManagerFactory("SistemaPU");
        EntityManager em = fabricaEM.createEntityManager();

        try {
            Estado estado = em.find(Estado.class,1L);
            System.out.println(estado.toString());
        }catch(Exception e) {
            throw new RuntimeException(e);
        }finally{
            em.close();
            fabricaEM.close();
        }
    }

    public static void main(String[] args) {
        //inserir();
        //consultar();
        //inserirComCidadeExistenteNoBD();
        //inserirPessoaNaCidade();
        //inserirCidadesEmEstado();
        consultarEstado();

    }

}