package com.orcafastjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orcafastjpa.entidades.ProdutoFornecedor;

@Repository
public interface ProdutoFornecedorRepository extends JpaRepository<ProdutoFornecedor, Long>{
}
