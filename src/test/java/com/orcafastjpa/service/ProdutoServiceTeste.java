package com.orcafastjpa.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.orcafastjpa.entidade.dto.ProdutoDTO;
import com.orcafastjpa.entidades.Categoria;
import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.repository.ProdutoRepository;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTeste {
	
	private Long idExistente;
	private Long idNaoExistente;
	
	private Produto produtoValido;
	private Produto produtoInvalido;
	
	private Categoria categoria;
	
	@BeforeEach
	void setup() {
		
		idExistente = 1l;
		idNaoExistente = 1000l;
		
		categoria = new Categoria("Alguma coisa");
		produtoValido = new Produto(categoria, "Vidro Blindex", "Itau", 1000, null);
		produtoInvalido = new Produto(categoria, null, "Itau", 1000, null);
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(produtoInvalido);
		Mockito.when(repository.save(produtoValido)).thenReturn(produtoValido);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new Produto()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
	}
	
	@InjectMocks
	ProdutoService service;
	
	@Mock
	ProdutoRepository repository;
	
	@Test
	public void retornaProdutoQuandoAlterarComSucesso() {
		produtoValido.setId(idExistente);
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(produtoValido));
		ProdutoDTO produto = service.editarProduto(idExistente, new ProdutoDTO(produtoValido));
		Assertions.assertNotNull(produto);
		Assertions.assertEquals(idExistente, produto.getId());
		Assertions.assertEquals(produtoValido.getCategoria(), produto.getCategoria());
		Assertions.assertEquals(produtoValido.getDescricaop(), produto.getDescricaop());
		Assertions.assertEquals(produtoValido.getMarca(), produto.getMarca());
		Assertions.assertEquals(produtoValido.getImagem(), produto.getImagem());
		Mockito.verify(repository).save(produtoValido);
	}
	
	@Test
	public void retornaNadaAoExcluirComIdProduto() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluirProduto(idExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idExistente);
	}
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> service.salvarProduto(produtoInvalido));
		Mockito.verify(repository).save(produtoInvalido);
	}
	
	@Test
	public void retornaContactDTOQuandoSalvarComSucesso() {
		ProdutoDTO produtoDTO = service.salvarProduto(produtoValido);
		Assertions.assertNotNull(produtoDTO);
		Mockito.verify(repository).save(produtoValido);
	}
	
	@Test
	public void retornaNadaAoExcluirComIdExistente() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluirProduto(idExistente);
		});
		
		Mockito.verify(repository,Mockito.times(1)).deleteById(idExistente);
	}
	
	@Test 
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.excluirProduto(idNaoExistente);
		});
		
		Mockito.verify(repository,Mockito.times(1)).deleteById(idNaoExistente);
	}
	
	@Test
	public void consultarPorIdRetornaProdutos() {
		ProdutoDTO prod = service.consultarProdutoId(idExistente);
		Assertions.assertNotNull(prod);
		
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test 
	public void lan??aEntidadeNaoEncontradaQuandoConsultaNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarProdutoId(idNaoExistente);
		});
		
		Mockito.verify(repository,Mockito.times(1)).findById(idNaoExistente);
	}
	

}
