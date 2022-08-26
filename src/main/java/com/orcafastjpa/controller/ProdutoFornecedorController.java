package com.orcafastjpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcafastjpa.entidades.ProdutoFornecedor;
import com.orcafastjpa.service.ProdutoFornecedorService;

@RestController
@RequestMapping("/")
public class ProdutoFornecedorController {
	
	@Autowired
	ProdutoFornecedorService service;
	
	@PostMapping("/produtofornecedor")
	public ResponseEntity<ProdutoFornecedor> salvarProdutoFornecedor(@Valid @RequestBody ProdutoFornecedor produtofornecedor) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(produtofornecedor));
	}
	 
	@GetMapping("/produtofornecedor")
	public ResponseEntity<List<ProdutoFornecedor>> consultarProdutoFornecedor() {
		return ResponseEntity.ok(service.consultarProdutoFornecedor());
	}
	
	@GetMapping("/produtofornecedor/{idprodutofornecedor}")
	public ResponseEntity<ProdutoFornecedor> consultarProdutoFornecedorId(@PathVariable("idprodutofornecedor") Long idprodutofornecedor) {
		return ResponseEntity.ok(service.consultarProdutoFornecedorId(idprodutofornecedor));
	}
	
	@DeleteMapping("/produtofornecedor/{idprodutofornecedor}")
	public ResponseEntity<Void> excluirProdutoFornecedor(@PathVariable("idprodutofornecedor") Long idprodutofornecedor) {
		service.excluirProdutoFornecedor(idprodutofornecedor);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/produtofornecedor/{idprodutofornecedor}")
	public ResponseEntity<ProdutoFornecedor> editarProdutoFornecedor(@PathVariable("idprodutofornecedor") Long idprodutofornecedor, @RequestBody ProdutoFornecedor produtofornecedor) {
		return ResponseEntity.ok(service.editarProdutoFornecedor(idprodutofornecedor, produtofornecedor));
	}	
}
