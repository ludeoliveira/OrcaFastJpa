package com.orcafastjpa.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 100, nullable = false, unique = true)
	private String razaosocial;
	@Column(length = 20, nullable = false, unique = true)
	private String cnpj;
	@Column(length = 100, nullable = false, unique = true)
	private String email;
	@Column(length = 15, nullable = false)
	private String telefone;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
