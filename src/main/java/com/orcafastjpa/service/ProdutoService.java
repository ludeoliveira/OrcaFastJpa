package com.orcafastjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repo;

	public Produto salvarProduto(Produto produto) {
		return repo.save(produto);
	}

	public List<Produto> consultarProdutos() {
		List<Produto> produto = repo.findAll();
		return produto;
	}

	public Produto consultarProdutoId(Long idproduto) {
		Produto produto = repo.findById(idproduto).get();
		return produto;
	}

	public void excluirProduto(Long idproduto) {
		Produto produto = consultarProdutoId(idproduto);
		repo.delete(produto);
	}

	public Produto editarProduto(Long idproduto, Produto produto) {
		Produto prod = consultarProdutoId(idproduto);
		prod.setCategoria(produto.getCategoria());
		prod.setDescricaop(produto.getDescricaop());
		prod.setMarca(produto.getMarca());
		prod.setEstoque(produto.getEstoque());
		prod.setImagem(produto.getImagem());
		return repo.save(prod);
	}

}
