package com.orcafastjpa.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
	@Id
	@Column(name ="idfornecedor")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Razão social é obrigatório")
	@Column(length = 100, nullable = false, unique = true)
	private String razaosocial;
	
	@NotBlank(message = "CNPJ é obrigatório")
	@Size(min = 18, max = 18, message = "O CNPJ deve conter 18 caracteres")
	@Column(length = 20, nullable = false, unique = true)
	private String cnpj;
	
	@NotBlank(message = "Email é obrigatório")
	@Column(length = 100, nullable = false, unique = true)
	@Email(message = "Email invalido")
	private String email;
	
	@NotBlank(message = "Telefone é obrigatório")
	@Column(length = 15, nullable = false)
	@Size(min = 14, max = 14, message = "O telefone deve conter 14 caracteres")
	private String telefone;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Fornecedor(String razaosocial, String cnpj, String email, String telefone) {
		this.razaosocial = razaosocial;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
	}
	public Fornecedor() {
	}
	
	
}
