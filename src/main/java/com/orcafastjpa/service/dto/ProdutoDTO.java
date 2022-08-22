package com.orcafastjpa.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.orcafastjpa.entidades.Categoria;
import com.orcafastjpa.entidades.Produto;

public class ProdutoDTO {
	
	private Long id;
	private Categoria categoria;
	private String descricaop;
	private String marca;
	private String imagem;
	

	public ProdutoDTO() {

	}

	public ProdutoDTO(Long id, Categoria categoria, String descricaop, String marca, String imagem) {
		this.id = id;
		this.categoria = categoria;
		this.descricaop = descricaop;
		this.marca = marca;
		this.imagem = imagem;
	}
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.categoria = produto.getCategoria();
		this.descricaop = produto.getDescricaop();
		this.marca = produto.getMarca();
		this.imagem = produto.getImagem();
	}
	
	
	public static List<ProdutoDTO> converterParaDTO(List<Produto> produtos){
		List<ProdutoDTO> produtosDTO = new ArrayList<>();
		for (Produto prod : produtos) {
			produtosDTO.add(new ProdutoDTO(prod));
		}
		
		return produtosDTO;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescricaop() {
		return descricaop;
	}

	public void setDescricaop(String descricaop) {
		this.descricaop = descricaop;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}


}
