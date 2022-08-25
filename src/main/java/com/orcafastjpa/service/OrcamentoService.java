package com.orcafastjpa.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		Optional<Orcamento> opOrcamento = repo.findById(idorcamento);
		Orcamento orcamento = opOrcamento.orElseThrow(() -> new EntityNotFoundException("Orcamento não encontrado"));
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
	
//	public List<Orcamento> consultarOrcamentoPorDataDeCriacao(Date datacriacao1, Date datacriacao2) {
//		List<Orcamento> orcamentopordata = repo.findByDatacriacao(datacriacao1, datacriacao2);
//		return orcamentopordata;
//	}
}
