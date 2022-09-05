package com.orcafastjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orcafastjpa.entidades.Orcamento;
import com.orcafastjpa.entidades.Selecao;


@Repository
public interface SelecaoRepository extends JpaRepository<Selecao, Long>{
	@Query("select sl from Selecao sl, Orcamento oc where sl.orcamento = oc.id and oc.id = ?1")
	List<Selecao> findByIdorcamento(Long idorcamento);
}
