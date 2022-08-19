package com.orcafastjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orcafastjpa.entidades.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
