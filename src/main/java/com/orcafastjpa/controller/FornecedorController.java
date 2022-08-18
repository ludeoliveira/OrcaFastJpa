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

import com.orcafastjpa.entidades.Fornecedor;
import com.orcafastjpa.service.FornecedorService;

@RestController
@RequestMapping("/")
public class FornecedorController {

	@Autowired
	FornecedorService service;

	@PostMapping("/fornecedor")
	public ResponseEntity<Fornecedor> salvarFornecedor(@RequestBody Fornecedor fornecedor) {
		return ResponseEntity.ok(service.salvar(fornecedor));
	}

	@GetMapping("/fornecedor")
	public ResponseEntity<List<Fornecedor>> consultarFornecedor() {
		return ResponseEntity.ok(service.consultarFornecedor());
	}

	@GetMapping("/fornecedor/{idfornecedor}")
	public ResponseEntity<Fornecedor> consultarFornecedorId(@PathVariable("idfornecedor") Long idfornecedor) {
		return ResponseEntity.ok(service.consultarFornecedorId(idfornecedor));
	}

	@DeleteMapping("/fornecedor/{idfornecedor}")
	public ResponseEntity<Void> excluirFornecedor(@PathVariable("idfornecedor") Long idfornecedor) {
		service.excluirFornecedor(idfornecedor);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/fornecedor/{idfornecedor}")
	public ResponseEntity<Fornecedor> editarFornecedor(@PathVariable("idfornecedor") Long idfornecedor,
			@RequestBody Fornecedor fornecedor) {
		return ResponseEntity.ok(service.editarFornecedor(idfornecedor, fornecedor));
	}
}