package com.orcafastjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.orcafastjpa.entidades.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	List<Produto> findByDescricaop(String descricaop);
	
	@Query("select pd from Produto pd, Categoria ct where pd.categoria = ct.id and ct.descricaoc = ?1")
	List<Produto> findByCategoria(String descricaoc);
	
	

}
