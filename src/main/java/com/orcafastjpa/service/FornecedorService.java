package com.orcafastjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcafastjpa.entidades.Fornecedor;
import com.orcafastjpa.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	FornecedorRepository repo;

	public Fornecedor salvar(Fornecedor fornecedor) {
		return repo.save(fornecedor);
	}

	public List<Fornecedor> consultarFornecedor() {
		List<Fornecedor> fornecedor = repo.findAll();
		return fornecedor;
	}

	public Fornecedor consultarFornecedorId(Long idfornecedor) {
		Fornecedor fornecedor = repo.findById(idfornecedor).get();
		return fornecedor;
	}

	public void excluirFornecedor(Long idfornecedor) {
		Fornecedor fornecedor = consultarFornecedorId(idfornecedor);
		repo.delete(fornecedor);
	}

	public Fornecedor editarFornecedor(Long idfornecedor, Fornecedor fornecedor) {
		Fornecedor forn = consultarFornecedorId(idfornecedor);
		forn.setRazaosocial(fornecedor.getRazaosocial());
		forn.setCnpj(fornecedor.getCnpj());
		forn.setEmail(fornecedor.getEmail());
		forn.setTelefone(fornecedor.getTelefone());
		return repo.save(forn);
	}
}
