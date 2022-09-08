package com.orcafastjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.orcafastjpa.entidades.ProdutoFornecedor;

@Repository
public interface ProdutoFornecedorRepository extends JpaRepository<ProdutoFornecedor, Long>{
	@Query("select pf from ProdutoFornecedor pf, Produto prod where pf.produto = prod.id and  prod.id = ?1")
	List <ProdutoFornecedor> findByProdutoFornecedor(Long idproduto);
}
