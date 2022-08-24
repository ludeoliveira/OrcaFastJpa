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
	//VOLTAR PARA COLOCAR A REF. DOS PRODUTOS PARA APAGAR JUNTO COM A CATEGORIA
	public void excluirCategoria(Long idcategoria) {
		repo.deleteById(idcategoria);
	}
	
	public Categoria editarCategoria(Long idcategoria, Categoria categoria) {
		Categoria cat = consultarCategoriaId(idcategoria);
		cat.setDescricaoc(categoria.getDescricaoc());
		return repo.save(cat);
	}
	
	
}
