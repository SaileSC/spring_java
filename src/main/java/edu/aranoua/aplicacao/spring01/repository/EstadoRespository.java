package edu.aranoua.aplicacao.spring01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.aranoua.aplicacao.spring01.model.Estado;

import java.util.Optional;

@Repository
public interface EstadoRespository extends JpaRepository<Estado, Long>{
   Optional<Estado> findByNome(String nome);
}
