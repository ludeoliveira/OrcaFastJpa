package com.orcafastjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.orcafastjpa.entidades.Selecao;


@Repository
public interface SelecaoRepository extends JpaRepository<Selecao, Long>{

}
