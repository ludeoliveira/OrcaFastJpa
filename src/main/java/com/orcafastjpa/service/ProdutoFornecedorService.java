package com.orcafastjpa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcafastjpa.entidades.ProdutoFornecedor;
import com.orcafastjpa.repository.ProdutoFornecedorRepository;

@Service
public class ProdutoFornecedorService {
	
	@Autowired
	ProdutoFornecedorRepository repo;
	
	public ProdutoFornecedor salvar(ProdutoFornecedor produtofornecedor) {
		return repo.save(produtofornecedor);
	}
	
	public List<ProdutoFornecedor> consultarProdutoFornecedor() {
		List<ProdutoFornecedor> produtofornecedor = repo.findAll();
		return produtofornecedor;
	}
	
	public ProdutoFornecedor consultarProdutoFornecedorId(Long idprodutofornecedor) { 
		Optional<ProdutoFornecedor> opProdForn = repo.findById(idprodutofornecedor);
		ProdutoFornecedor produtofornecedor = opProdForn.orElseThrow(() -> new EntityNotFoundException("Entidade ProdutoFornecedor não encontrada"));
		return produtofornecedor;
	}
	
	public void excluirProdutoFornecedor(Long idprodutofornecedor) {
		//ProdutoFornecedor produtofornecedor = consultarProdutoFornecedorId(idprodutofornecedor);
		repo.deleteById(idprodutofornecedor);
	}
	
	public ProdutoFornecedor editarProdutoFornecedor(Long idprodutofornecedor, ProdutoFornecedor produtofornecedor) {
		ProdutoFornecedor prodforn = consultarProdutoFornecedorId(idprodutofornecedor);
		prodforn.setFornecedor(produtofornecedor.getFornecedor());
		prodforn.setProduto(produtofornecedor.getProduto());
		prodforn.setPreco(produtofornecedor.getPreco());
		return repo.save(prodforn);
	}
	
}
