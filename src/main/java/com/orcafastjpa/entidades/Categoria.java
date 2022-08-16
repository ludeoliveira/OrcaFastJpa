package com.orcafastjpa.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idcategoria")
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String descricaoc;

	public Categoria() {
		
	}

	public Categoria(String descricao) {
		this.descricaoc = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricaoc;
	}

	public void setDescricao(String descricao) {
		this.descricaoc = descricao;
	}
	
	
	

}
