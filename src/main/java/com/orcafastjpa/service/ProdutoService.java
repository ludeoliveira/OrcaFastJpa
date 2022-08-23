package com.orcafastjpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.repository.ProdutoRepository;
import com.orcafastjpa.service.dto.ProdutoDTO;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repo;

	public ProdutoDTO salvarProduto(Produto produto) {
		Produto prod = repo.save(produto);
		ProdutoDTO produtoDTO = new ProdutoDTO(prod);
		
		return produtoDTO;
	}

	public List<ProdutoDTO> consultarProdutos() {
		List<Produto> produto = repo.findAll();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		
		for (Produto prod: produto) {
			produtosDTO.add(new ProdutoDTO(prod));
		}
		
		return produtosDTO;
	}

	private Produto consultarProdutoIdprivate(Long idproduto) {
		Produto produto = repo.findById(idproduto).get();
		return produto;
	}
	
	public ProdutoDTO consultarProdutoId(Long idproduto) {
		Optional<Produto> operacaoProd = repo.findById(idproduto);
		Produto prod = operacaoProd.orElseThrow(
				() -> new EntityNotFoundException("Produto não encontrado"));
				
		return new ProdutoDTO(prod);
	}

	public void excluirProduto(Long idproduto) {
		//Produto produto = consultarProdutoIdprivate(idproduto);
		//repo.delete(produto);
		repo.deleteById(idproduto);
	}

	public ProdutoDTO editarProduto(Long idproduto, Produto produto) {
		Produto prod = consultarProdutoIdprivate(idproduto);
		BeanUtils.copyProperties(produto, prod, "id");
		/*
		prod.setCategoria(produto.getCategoria());
		prod.setDescricaop(produto.getDescricaop());
		prod.setMarca(produto.getMarca());
		prod.setEstoque(produto.getEstoque());
		prod.setImagem(produto.getImagem());
		*/
		return new ProdutoDTO (repo.save(prod));
	}

}
