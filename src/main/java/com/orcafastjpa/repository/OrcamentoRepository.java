package com.orcafastjpa.repository;


import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orcafastjpa.entidades.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>{
//	@Query("SELECT * FROM Orcamento WHERE datacriacao BETWEEN ?1 AND ?2")
//	List<Orcamento> findByDatacriacao(String datacriacao1 ,String datacriacao2);
	List<Orcamento> findByDatacriacao(Instant instanteconvertido);
}

//SELECT * FROM Products
//WHERE Price BETWEEN 10 AND 20;

//SELECT column_name(s)
//FROM table_name
//WHERE column_name BETWEEN value1 AND value2;