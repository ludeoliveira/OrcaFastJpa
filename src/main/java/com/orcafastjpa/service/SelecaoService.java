package com.orcafastjpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcafastjpa.entidade.dto.SelecaoDTO;
import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.entidades.Selecao;
import com.orcafastjpa.repository.ProdutoRepository;
import com.orcafastjpa.repository.SelecaoRepository;

@Service
public class SelecaoService {
	@Autowired
	SelecaoRepository repo;
	
	@Autowired
	ProdutoRepository pRepo;
	
	private SelecaoDTO converteDTO(Selecao sel) {
		Long idProduto = sel.getProduto().getId();
		Produto p = pRepo.findById(idProduto).get();
		String descricaop = p.getDescricaop();
		SelecaoDTO sAux = new SelecaoDTO(sel.getId(), sel.getQuantidade(), sel.getPreco(), sel.getProduto(), sel.getOrcamento(), descricaop);
		return sAux;
	}
	
	public List<SelecaoDTO> consultarSelecao(){
		List<Selecao> selecao = repo.findAll();
		List<SelecaoDTO> selDTO = new ArrayList<>();
		for(Selecao sel: selecao) {
			SelecaoDTO sAux = this.converteDTO(sel);
			selDTO.add(sAux);
		}
		return selDTO;
	}
	
	public SelecaoDTO consultarSelecaoPorId(Long idselecao) {
		Optional<Selecao> opSel = repo.findById(idselecao);
		Selecao selecao = opSel.orElseThrow(() -> new EntityNotFoundException("Seleção não encontrada"));
		SelecaoDTO selDTO = this.converteDTO(selecao);
		return selDTO;	
	}
	
	public SelecaoDTO salvarSelecao (Selecao selecao) {
		Selecao sel = repo.save(selecao);
		SelecaoDTO selecaoDTO = this.converteDTO(sel);
		return selecaoDTO;
	}
	
	
	private Selecao consultarSelecaoIdprivate(Long idselecao) {
		Optional<Selecao> opSel = repo.findById(idselecao);
		Selecao selecao = opSel.orElseThrow(() -> new EntityNotFoundException("Seleção não encontrada"));
		return selecao;	
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
		SelecaoDTO sDTO = this.converteDTO(repo.save(sel));
		return sDTO;
	}
	
	public List<SelecaoDTO> consultarSelecaoPorOrcamento(Long idorcamento){
		List<Selecao> selecao = repo.findByIdorcamento(idorcamento);
		List<SelecaoDTO> selDTO = new ArrayList<>();
		for(Selecao sel: selecao) {
			SelecaoDTO sAux = this.converteDTO(sel);
			selDTO.add(sAux);
		}
		return selDTO;
	}
}
