package com.orcafastjpa.entidade.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.orcafastjpa.entidades.Orcamento;
import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.entidades.Selecao;
import com.orcafastjpa.repository.ProdutoRepository;

public class SelecaoDTO {

	private Long id;
	private double quantidade;
	private double preco;
	private Produto produto;
	private Orcamento orcamento;
	private String descricaop;
	
	@Autowired
	ProdutoRepository repo;
	
	
	public SelecaoDTO() {

	}
	
	public SelecaoDTO(Selecao selecao) {
		this.quantidade = selecao.getQuantidade();
		this.preco = selecao.getPreco();
		this.produto = selecao.getProduto();
		this.orcamento = selecao.getOrcamento();
		
		/*
		Long idproduto = 1l;
		Produto p = repo.findById(idproduto).get();
		this.descricaop = p.getDescricaop();
		*/
		
		Long idproduto = 1l;
		Produto obj = repo.getOne(idproduto);
		this.descricaop = obj.getDescricaop();
	
		/*
		if(obj.isPresent()) {
			Produto p = obj.get();
			this.descricaop = p.getDescricaop();		
		} else {
			this.descricaop = "Teste descrição";
		}
		*/

	}
	
	public SelecaoDTO(double quantidade, double preco, Produto produto, Orcamento orcamento, String descricaop) {
		this.quantidade = quantidade;
		this.preco = preco;
		this.produto = produto;
		this.orcamento = orcamento;
		this.descricaop = descricaop;
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
