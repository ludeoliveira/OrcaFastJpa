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
	
	public Selecao() {}

	public Selecao(double quantidade, double preco, Produto produto, Orcamento orcamento) {
		this.quantidade = quantidade;
		this.preco = preco;
		this.produto = produto;
		this.orcamento = orcamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	
}
