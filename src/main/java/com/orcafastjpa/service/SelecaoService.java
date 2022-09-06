package com.orcafastjpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcafastjpa.entidade.dto.SelecaoDTO;
import com.orcafastjpa.entidades.Selecao;
import com.orcafastjpa.repository.SelecaoRepository;

@Service
public class SelecaoService {
	@Autowired
	SelecaoRepository repo;
	
	public SelecaoDTO salvarSelecao (Selecao selecao) {
		Selecao sel = repo.save(selecao);
		SelecaoDTO selecaoDTO = new SelecaoDTO(sel);
		
		return selecaoDTO;
	}
	
	public List<SelecaoDTO> consultarSelecao(){
		List<Selecao> selecao = repo.findAll();
		List<SelecaoDTO> selecaoDTO = new ArrayList<SelecaoDTO>();
		
		for (Selecao sel: selecao) {
			selecaoDTO.add(new SelecaoDTO(sel));
		}
		
		return selecaoDTO;
	}
	
	private Selecao consultarSelecaoIdprivate(Long idselecao) {
		Optional<Selecao> opSel = repo.findById(idselecao);
		Selecao selecao = opSel.orElseThrow(() -> new EntityNotFoundException("Seleção não encontrada"));
		return selecao;	
	}
	
	public SelecaoDTO consultarSelecaoId(Long idselecao) {
		Optional<Selecao> opSel = repo.findById(idselecao);
		Selecao sel = opSel.orElseThrow(
				() -> new EntityNotFoundException("Seleção não encontrada"));
		
		return new SelecaoDTO(sel);	
	}
	
	public void excluirSelecao(Long idselecao) {
		//Selecao selecao = consultarSelecaoPorId(idselecao);
		repo.deleteById(idselecao);
	}
	
	public SelecaoDTO editarSelecao(Long idselecao, SelecaoDTO selecao) {
		Selecao sel = consultarSelecaoIdprivate(idselecao);
		BeanUtils.copyProperties(selecao, sel, "id");
		/*
		select.setId(selecao.getId());
		select.setOrcamento(selecao.getOrcamento());
		select.setPreco(selecao.getPreco());
		select.setProduto(selecao.getProduto());
		select.setQuantidade(selecao.getQuantidade());
		*/
		return new SelecaoDTO (repo.save(sel));
	}
	
	public List<Selecao> consultarSelecaoPorOrcamento(Long idorcamento){
		List<Selecao> selecao = repo.findByIdorcamento(idorcamento);
		return selecao;
	}
}
