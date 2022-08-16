package com.orcafastjpa.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="idproduto")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idcategoria")
	private Categoria categoria;
	
	@Column(length = 100, nullable = false)
	private String descricaop;
	
	@Column(length = 50, nullable = false)
	private String marca;
	
	@Column(nullable = false)
	private double estoque;
	
	@Column(length = 300)
	private String imagem;

	public Produto(Categoria categoria, String descricaop, String marca, double estoque, String imagem) {
		this.categoria = categoria;
		this.descricaop = descricaop;
		this.marca = marca;
		this.estoque = estoque;
		this.imagem = imagem;
	}

	public Produto() {

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

	public double getEstoque() {
		return estoque;
	}

	public void setEstoque(double estoque) {
		this.estoque = estoque;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}	

}
