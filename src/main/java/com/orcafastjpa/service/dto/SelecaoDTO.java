package com.orcafastjpa.service.dto;

import java.util.Optional;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.orcafastjpa.entidades.Orcamento;
import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.entidades.Selecao;
import com.orcafastjpa.repository.ProdutoFornecedorRepository;
import com.orcafastjpa.repository.ProdutoRepository;

public class SelecaoDTO {
	private Long id;
	private double preco;
	private Produto produto;
	private Orcamento orcamento;
	private String descricaop;
	

	public SelecaoDTO() { }

	public SelecaoDTO(Long id, double preco, Produto produto, Orcamento orcamento, String descricaop) {
		this.id = id;
		this.preco = preco;
		this.produto = produto;
		this.orcamento = orcamento;
		this.descricaop = descricaop;	
	}

	
//	public SelecaoDTO(Selecao selecao) {
//		this.id = selecao.getId();
//		this.preco = selecao.getPreco();
//		this.produto = selecao.getProduto();
//		this.orcamento = selecao.getOrcamento();
//		Optional<Produto> obj = pRepo.findById(selecao.getProduto().getId());
//		//this.descricaop = p.getDescricaop();
//	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
