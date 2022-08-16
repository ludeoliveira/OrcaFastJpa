package com.orcafastjpa.entidades;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "orcamento")
public class Orcamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	

	@ManyToOne
	@JoinColumn(name = "idusuario")
	private Usuario usuario;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant datacriacao;

	public Orcamento() {
		
	}

	public Orcamento(Usuario usuario, Instant datacriacao) {
		this.usuario = usuario;
		this.datacriacao = datacriacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Instant getdatacriacao() {
		return datacriacao;
	}

	public void setCreatedAt(Instant datacriacao) {
		this.datacriacao = datacriacao;
	}

}
