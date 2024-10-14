package edu.aranoua.aplicacao.spring01.repository;


import edu.aranoua.aplicacao.spring01.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRespository extends JpaRepository<Pais, Long> {
    Pais findByNome(String nome);
}
