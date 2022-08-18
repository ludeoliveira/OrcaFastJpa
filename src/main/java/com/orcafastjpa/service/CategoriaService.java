package com.orcafastjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcafastjpa.entidades.Categoria;
import com.orcafastjpa.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repo;
	
	public Categoria salvar(Categoria categoria) {
		return repo.save(categoria);
	}
	
	public List<Categoria> consultarCategorias(){
		List<Categoria> categorias = repo.findAll();
		return categorias;
	}
	
	public Categoria consultarCategoriaId(Long idcategoria) {
		Categoria categoria = repo.findById(idcategoria).get();
		return categoria;
	}
	
	public void excluirCategoria(Long idcategoria) {
		Categoria categoria = consultarCategoriaId(idcategoria);
		repo.delete(categoria);
	}
	
	public Categoria editarCategoria(Long idcategoria, Categoria categoria) {
		Categoria cat = consultarCategoriaId(idcategoria);
		cat.setDescricaoc(categoria.getDescricaoc());
		return repo.save(cat);
	}
	
	
}
