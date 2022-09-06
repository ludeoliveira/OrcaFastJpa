package com.orcafastjpa.entidade.dto;

import com.orcafastjpa.entidades.Orcamento;
import com.orcafastjpa.entidades.Produto;


public class SelecaoDTO{

	private Long id;
	private double quantidade;
	private double preco;
	private Produto produto;
	private Orcamento orcamento;
	private String descricaop;
	


	public SelecaoDTO(Long id, double quantidade, double preco, Produto produto, Orcamento orcamento, String descricaop) {
		this.id = id;
		this.quantidade = quantidade;
		this.preco = preco;
		this.produto = produto;
		this.orcamento = orcamento;
		this.descricaop = descricaop;	
	}
	
	public SelecaoDTO() {

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

	public String getDescricaop() {
		return descricaop;
	}

	public void setDescricaop(String descricaop) {
		this.descricaop = descricaop;
	}
	
}
