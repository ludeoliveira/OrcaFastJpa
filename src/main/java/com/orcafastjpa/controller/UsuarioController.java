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

import com.orcafastjpa.entidades.Usuario;
import com.orcafastjpa.service.UsuarioService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class UsuarioController {
	
	@Autowired
	UsuarioService service;
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> salvarUsuario(@Valid @RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(usuario));
	}
	
	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> consultarUsuario(){
		return ResponseEntity.ok(service.consultarUsuario());
	} 
	
	@GetMapping("/usuario/{idusuario}")
	public ResponseEntity<Usuario> consultarUsuarioById(@PathVariable("idusuario") Long idusuario){
		return ResponseEntity.ok(service.consultarUsuarioById(idusuario));
	}
	//VOLTAR PARA COLOCAR A REF. DOS ORÇAMENTOS PARA APAGAR JUNTO COM O USUÁRIO
	@DeleteMapping("/usuario/{idusuario}")
	public ResponseEntity<Void> excluirUsuario(@PathVariable("idusuario") Long idusuario){
		service.excluirUsuario(idusuario);
		return ResponseEntity.noContent().build();	
	}
	
	@PutMapping("/usuario/{idusuario}")
	public ResponseEntity<Usuario> editarUsuario(@PathVariable("idusuario")
	Long idusuario, @RequestBody Usuario usuario){
		return ResponseEntity.ok(service.editarUsuario(idusuario, usuario));
	}
}

