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

import com.orcafastjpa.entidades.Categoria;
import com.orcafastjpa.service.CategoriaService;

@RestController
@RequestMapping("/")
public class CategoriaController {
	
	@Autowired
	CategoriaService service;
	
	@GetMapping
	public String home() {
		return "Welcome to home page";
	}
	
	@PostMapping("/categoria")
	public ResponseEntity<Categoria> salvaCategoria(@RequestBody Categoria categoria){
		return ResponseEntity.ok(service.salvar(categoria));
	}
	
	@GetMapping("/categoria")
	public ResponseEntity<List<Categoria>> consultarCategoria() {
		return ResponseEntity.ok(service.consultarCategorias());
	}
	
	@GetMapping("/categoria/{idcategoria}")
	public ResponseEntity<Categoria> consultaCategoriaId(@PathVariable("idcategoria")Long idcategoria){
		return ResponseEntity.ok(service.consultarCategoriaId(idcategoria));
	}
	
	@DeleteMapping("/categoria/{idcategoria}")
	public ResponseEntity<Void> excluirCategoria(@PathVariable("idcategoria") Long idcategoria) {
		service.excluirCategoria(idcategoria);
		return ResponseEntity.noContent().build(); 
	}
	
	@PutMapping("/categoria/{idcategoria}")
	public ResponseEntity<Categoria> editarContato(@PathVariable("idcategoria")
		Long idcategoria, @RequestBody Categoria categoria) {
		return ResponseEntity.ok(service.editarCategoria(idcategoria, categoria));
	}
	
}