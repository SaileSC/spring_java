package edu.aranoua.aplicacao.spring01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.aranoua.aplicacao.spring01.model.Estado;

public interface EstadoRespository extends JpaRepository<Estado, Integer>{

    
} 
