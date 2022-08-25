package com.orcafastjpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orcafastjpa.entidades.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long>{
//	@Query("SELECT datacriacao FROM Orcamento WHERE datacriacao BETWEEN ?1 AND ?2")
//	List<Orcamento> findByDatacriacao(Date datacriacao1, Date datacriacao2);
}

//SELECT * FROM Products
//WHERE Price BETWEEN 10 AND 20;

//SELECT column_name(s)
//FROM table_name
//WHERE column_name BETWEEN value1 AND value2;