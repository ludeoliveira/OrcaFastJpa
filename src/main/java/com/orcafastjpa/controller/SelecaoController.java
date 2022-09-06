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

import com.orcafastjpa.entidade.dto.SelecaoDTO;
import com.orcafastjpa.entidades.Selecao;
import com.orcafastjpa.service.SelecaoService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class SelecaoController {
	
	@Autowired
	SelecaoService service;
	
	@PostMapping("/selecao")
	public ResponseEntity<Selecao> salvarSelecao(@Valid @RequestBody Selecao selecao){
		Selecao sel = service.salvarSelecao(selecao);
		return ResponseEntity.status(HttpStatus.CREATED).body(sel);
	}
	
	@GetMapping("/selecao")
	public ResponseEntity<List<SelecaoDTO>> consultarSelecao(){
		return ResponseEntity.ok(service.consultarSelecao());
	}
	
	@GetMapping("/selecao/{idselecao}")
	public ResponseEntity<SelecaoDTO>consultarSelecaoId(@PathVariable("idselecao") Long idselecao){
		return ResponseEntity.status(HttpStatus.OK).body(service.consultarSelecaoPorId(idselecao));
	}
	
	@DeleteMapping("/selecao/{idselecao}")
	public ResponseEntity<Void> excluirSelecao(@PathVariable("idselecao") Long idselecao){
		service.excluirSelecao(idselecao);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/selecao/{idselecao}")
	public ResponseEntity<SelecaoDTO> editarUsuario(@PathVariable("idselecao") Long idselecao,
			@RequestBody SelecaoDTO selecao){
		return ResponseEntity.status(HttpStatus.OK).body(service.editarSelecao(idselecao, selecao));
	}

	
	@GetMapping("/selecao/orcamento/{idorcamento}")
	public ResponseEntity<List<SelecaoDTO>> consultarSelecaoByOrcamento(@PathVariable("idorcamento") Long idorcamento){
		return ResponseEntity.ok(service.consultarSelecaoPorOrcamento(idorcamento));
	}
}
	

	

