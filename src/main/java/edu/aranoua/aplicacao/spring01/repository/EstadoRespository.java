package edu.aranoua.aplicacao.spring01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.aranoua.aplicacao.spring01.model.Estado;

@Repository
public interface EstadoRespository extends JpaRepository<Estado, Long>{
   
}
