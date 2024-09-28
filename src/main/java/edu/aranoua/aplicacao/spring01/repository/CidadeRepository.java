package edu.aranoua.aplicacao.spring01.repository;

import edu.aranoua.aplicacao.spring01.model.cidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
