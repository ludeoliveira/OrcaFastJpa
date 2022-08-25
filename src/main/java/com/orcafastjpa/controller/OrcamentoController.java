package com.orcafastjpa.controller;


import java.time.Instant;
import java.util.List;

import javax.validation.Valid;

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

import com.orcafastjpa.entidades.Orcamento;
import com.orcafastjpa.service.OrcamentoService;

@RestController
@RequestMapping("/")
public class OrcamentoController {
	
	@Autowired
	OrcamentoService service;
	
	@PostMapping("/orcamento")
	ResponseEntity<Orcamento> salvarOrcamento(@Valid @RequestBody Orcamento orcamento){
		return ResponseEntity.ok(service.salvar(orcamento));
	}
	
	@GetMapping("/orcamento")
	ResponseEntity<List<Orcamento>> consultarOrcamentos(){
		return ResponseEntity.ok(service.consultarOrcamentos());
	}
	
	@GetMapping("/orcamento/{idorcamento}")
	public ResponseEntity<Orcamento> consultaOrcamentoId(@PathVariable("idorcamento")Long idorcamento){
		return ResponseEntity.ok(service.consultarOrcamentoById(idorcamento));
	}
	
	@DeleteMapping("/orcamento/{idorcamento}")
	public ResponseEntity<Void> excluirOrcamento(@PathVariable("idorcamento") Long idorcamento) {
		service.excluirOrcamento(idorcamento);
		return ResponseEntity.noContent().build(); 
	}
	
	@PutMapping("/orcamento/{idorcamento}")
	public ResponseEntity<Orcamento> editarContato(@PathVariable("idorcamento")
		Long idorcamento, @RequestBody Orcamento orcamento) {
		return ResponseEntity.ok(service.editarOrcamento(idorcamento, orcamento));
	}
	
	@GetMapping("/orcamento/datacriacao/{datacriacao}")
	public ResponseEntity<List<Orcamento>> consultarOrcamentoPorDataDeCriacao(@PathVariable("datacriacao") String datacriacao) {
//		Date data = Date.parse(datacriacao);
		Instant instanteconvertido = Instant.parse(datacriacao);
		return ResponseEntity.ok(service.consultarOrcamentoPorDataDeCriacao(instanteconvertido));
	}
	
//	Instant instant = zdt.toInstant() ;


}
