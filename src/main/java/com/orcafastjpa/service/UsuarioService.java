package com.orcafastjpa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.orcafastjpa.entidades.Usuario;
import com.orcafastjpa.repository.UsuarioRepository;
@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository repo;
	
	public Usuario salvar(Usuario usuario) {
		return repo.save(usuario);
	}
	
	public List<Usuario> consultarUsuario(){
		List<Usuario> usuarios = repo.findAll();
		return usuarios; 
	}
	
	public Usuario consultarUsuarioById(Long idusuario) {
		Optional<Usuario> opUsuario = repo.findById(idusuario);
		Usuario usuario = opUsuario.orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
		return usuario;
	}
	//VOLTAR PARA COLOCAR A REF. DOS ORÇAMENTOS PARA APAGAR JUNTO COM O USUÁRIO
	public void excluirUsuario(Long idusuario) {
		repo.deleteById(idusuario);
	}
	
	public Usuario editarUsuario(Long idusuario, Usuario usuario) {
		Usuario user = consultarUsuarioById(idusuario);
		user.setCnpj(usuario.getCnpj());
		user.setRazaosocial(usuario.getRazaosocial());
		return repo.save(user);
	}
	
	
	
	
	
	
	
	
}
