package com.orcafastjpa.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idcategoria")
	private Long id;
	
	@Column(length = 100, nullable = false, unique = true)
	@Size(min = 1, max = 100, message = "Descricao é obrigatória")
	private String descricaoc;

	
	public Categoria() {

	}
	public Categoria(String descricaoc) {
		this.descricaoc = descricaoc;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoc() {
		return descricaoc;
	}

	public void setDescricaoc(String descricaoc) {
		this.descricaoc = descricaoc;
	}

}
