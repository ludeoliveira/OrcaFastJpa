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
@Table(name = "produtofornecedor")
public class ProdutoFornecedor {
	
	@Id
	@Column(name ="idprodutofornecedor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idproduto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name="idfornecedor")
	private Fornecedor fornecedor;
	
	@Column(nullable = false)
	private double preco;

	public ProdutoFornecedor() {}

	public ProdutoFornecedor(Produto produto, Fornecedor fornecedor, double preco) {
		this.produto = produto;
		this.fornecedor = fornecedor;
		this.preco = preco;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
