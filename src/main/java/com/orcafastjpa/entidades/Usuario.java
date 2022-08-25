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
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false, unique = true)
	@NotBlank(message ="Razão social é obrigatório")
	private String razaosocial;
	
	@Column(length = 20, nullable = false, unique = true)
	@NotBlank(message ="CNPJ é obrigatório")
	@Size(min = 18, max = 18, message = "O CNPJ deve conter 18 caracteres")
	private String cnpj;
	
	public Usuario() {
	}

	public Usuario(String razaosocial, String cnpj) {
		super();
		this.razaosocial = razaosocial;
		this.cnpj = cnpj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	

}
