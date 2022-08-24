package com.orcafastjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcafastjpa.entidades.Categoria;
import com.orcafastjpa.entidades.Orcamento;
import com.orcafastjpa.repository.OrcamentoRepository;

@Service
public class OrcamentoService {

	@Autowired
	OrcamentoRepository repo;
	
	public Orcamento salvar(Orcamento orcamento) {
		return repo.save(orcamento);
	}
	
	public List<Orcamento> consultarOrcamentos(){
		List<Orcamento> orcamentos = repo.findAll();
		return orcamentos;
	}
	
	public Orcamento consultarOrcamentoById(Long idorcamento) {
		Orcamento orcamento = repo.findById(idorcamento).get();
		return orcamento;
	}
	
	public void excluirOrcamento(Long idorcamento) {
		//Orcamento orcamento = consultarOrcamentoById(idorcamento);
		repo.deleteById(idorcamento);
	}
	
	public Orcamento editarOrcamento(Long idorcamento, Orcamento orcamento) {
		Orcamento orc = consultarOrcamentoById(idorcamento);
		orc.setUsuario(orcamento.getUsuario());
		return repo.save(orc);
	}
}
