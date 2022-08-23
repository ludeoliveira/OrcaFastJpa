package com.orcafastjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcafastjpa.entidades.Selecao;
import com.orcafastjpa.repository.SelecaoRepository;

@Service
public class SelecaoService {
	@Autowired
	SelecaoRepository repo;
	
	public Selecao salvar (Selecao selecao) {
		return repo.save(selecao);
	}
	
	public List<Selecao> consultarSelecao(){
		List<Selecao> selecao = repo.findAll();
		return selecao;
	}
	
	public Selecao consultarSelecaoPorId(Long idselecao) {
		Selecao selecao = repo.findById(idselecao).get();
		return selecao;	
	}
	
	public void excluirSelecao(Long idselecao) {
		//Selecao selecao = consultarSelecaoPorId(idselecao);
		repo.deleteById(idselecao);
	}
	
	public Selecao editarSelecao(Long idselecao, Selecao selecao) {
		Selecao select = consultarSelecaoPorId(idselecao);
		select.setId(selecao.getId());
		select.setOrcamento(selecao.getOrcamento());
		select.setPreco(selecao.getPreco());
		select.setProduto(selecao.getProduto());
		select.setQuantidade(selecao.getQuantidade());
		return repo.save(select);
	}
}
