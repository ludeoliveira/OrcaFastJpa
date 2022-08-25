package com.orcafastjpa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

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
		Optional<Fornecedor> opForn = repo.findById(idfornecedor);
		Fornecedor fornecedor = opForn.orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado"));
		return fornecedor;
	}

	public void excluirFornecedor(Long idfornecedor) {
		
		//Fornecedor fornecedor = consultarFornecedorId(idfornecedor);
		repo.deleteById(idfornecedor);
	}

	@Transactional(rollbackOn = TransactionSystemException.class)
	public Fornecedor editarFornecedor(Long idfornecedor, Fornecedor fornecedor) {
		Fornecedor forn = consultarFornecedorId(idfornecedor);
		forn.setRazaosocial(fornecedor.getRazaosocial());
		forn.setCnpj(fornecedor.getCnpj());
		forn.setEmail(fornecedor.getEmail());
		forn.setTelefone(fornecedor.getTelefone());
		return repo.save(forn);
	}
}
