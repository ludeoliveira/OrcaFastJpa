package com.orcafastjpa.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "selecao")
public class Selecao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idselecao")
	private Long id;
	
	@Column(nullable = false)
	private double quantidade;
	
	@Column(nullable = false)
	private double preco;
	
	@ManyToOne
	@JoinColumn(name="idproduto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name="idorcamento")
	private Orcamento orcamento;
	
	
	
	
}
