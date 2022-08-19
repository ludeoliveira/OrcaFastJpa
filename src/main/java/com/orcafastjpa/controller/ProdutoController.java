package com.orcafastjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.service.ProdutoService;

@RestController
@RequestMapping("/")
public class ProdutoController {
	
	@Autowired
	ProdutoService service;

	@PostMapping("/produtos")
	public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto){
		return ResponseEntity.ok(service.salvarProduto(produto));
	}

	@GetMapping("/produtos")
	public ResponseEntity<List<Produto>> consultarProduto() {
		return ResponseEntity.ok(service.consultarProdutos());
	}

	@GetMapping("/produtos/{idproduto}")
	public ResponseEntity<Produto> consultarProdutoId(@PathVariable("idproduto") Long idproduto) {
		return ResponseEntity.ok(service.consultarProdutoId(idproduto));
	}

	@DeleteMapping("/produtos/{idproduto}")
	public ResponseEntity<Void> excluirProduto(@PathVariable("idproduto") Long idproduto) {
		service.excluirProduto(idproduto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/produtos/{idproduto}")
	public ResponseEntity<Produto> editarProduto(@PathVariable("idproduto") Long idproduto,
			@RequestBody Produto produto) {
		return ResponseEntity.ok(service.editarProduto(idproduto, produto));
	}
	
	

}
