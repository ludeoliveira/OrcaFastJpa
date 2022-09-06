package com.orcafastjpa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orcafastjpa.entidades.Selecao;
import com.orcafastjpa.service.SelecaoService;
import com.orcafastjpa.service.dto.SelecaoDTO;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class SelecaoController {
	@Autowired
	SelecaoService service;
	
	@PostMapping("/selecao")
	public ResponseEntity<Selecao> salvarSelecao(@Valid @RequestBody Selecao selecao){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(selecao));
	}
	
	@GetMapping("/selecao")
	public ResponseEntity<List<SelecaoDTO>> consultarSelecao(){
		return ResponseEntity.ok(service.consultarSelecao());
	}
	
	@GetMapping("/selecao/{idselecao}")
	public ResponseEntity<SelecaoDTO>consultarSelecaoById(@PathVariable("idselecao") Long idselecao){
		return ResponseEntity.ok(service.consultarSelecaoPorId(idselecao));
	}
	
	@GetMapping("/selecao/orcamento/{idorcamento}")
	public ResponseEntity<List<Selecao>> consultarSelecaoByOrcamento(@PathVariable("idorcamento") Long idorcamento){
		return ResponseEntity.ok(service.consultarSelecaoPorOrcamento(idorcamento));
	}
	
	@DeleteMapping("/selecao/{idselecao}")
	public ResponseEntity<Void> excluirSelecao(@PathVariable("idselecao") Long idselecao){
		service.excluirSelecao(idselecao);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/selecao/{idselecao}")
	public ResponseEntity<Selecao> editarUsuario(@PathVariable("idselecao") Long idselecao, @RequestBody Selecao selecao){
		return ResponseEntity.ok(service.editarSelecao(idselecao, selecao));
	}
}
