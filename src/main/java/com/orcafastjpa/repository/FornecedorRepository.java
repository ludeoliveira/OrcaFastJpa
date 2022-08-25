package com.orcafastjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orcafastjpa.entidades.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
	List<Fornecedor> findByEmail(String email);
}
