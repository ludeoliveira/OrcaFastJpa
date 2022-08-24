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

import com.orcafastjpa.entidades.Fornecedor;
import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.entidades.ProdutoFornecedor;
import com.orcafastjpa.repository.ProdutoFornecedorRepository;

@ExtendWith(SpringExtension.class)
public class ProdutoFornecedorSeviceTeste {

	private Long idProdutoFornecedorExistente;
	private Long idProdutoFornecedorNaoExistente;
	
	private ProdutoFornecedor produtoFornecedorValida;
	private ProdutoFornecedor produtoFornecedorInvalida;
	
	private Produto produto;
	private Fornecedor fornecedor; 
	
	@BeforeEach
	void setup() {
		idProdutoFornecedorExistente = 1l;
		idProdutoFornecedorNaoExistente = 1000l;
		
		produto = new Produto();
		fornecedor = new Fornecedor();
		
		produtoFornecedorValida = new ProdutoFornecedor(produto, fornecedor, 1.70);
		produtoFornecedorInvalida = new ProdutoFornecedor(produto, null, 1.34);
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(produtoFornecedorInvalida);
		Mockito.when(repository.save(produtoFornecedorValida)).thenReturn(produtoFornecedorValida);
		
		Mockito.doNothing().when(repository).deleteById(idProdutoFornecedorExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idProdutoFornecedorNaoExistente);
		
		Mockito.when(repository.findById(idProdutoFornecedorExistente)).thenReturn(Optional.of(new ProdutoFornecedor()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idProdutoFornecedorNaoExistente);
		
	}
	
	@InjectMocks
	ProdutoFornecedorService service;
	
	@Mock
	ProdutoFornecedorRepository repository;
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(produtoFornecedorInvalida));
		Mockito.verify(repository).save(produtoFornecedorInvalida);
	}
	
	@Test
	public void retornaProdutoFornecedorQuandoSalvarComSucesso() {
		ProdutoFornecedor produtoFornecedor = service.salvar(produtoFornecedorValida);
		Assertions.assertNotNull(produtoFornecedor);
		Mockito.verify(repository).save(produtoFornecedorValida);
	}
	
	@Test
	public void retornaExcecaoQuandoAlteraSemSucesso() {
		produtoFornecedorInvalida.setId(idProdutoFornecedorExistente);
		Mockito.when(repository.findById(idProdutoFornecedorExistente)).thenReturn(Optional.of(produtoFornecedorInvalida));
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.editarProdutoFornecedor(idProdutoFornecedorExistente, produtoFornecedorInvalida));
		Mockito.verify(repository).save(produtoFornecedorInvalida);
		
	}
	
	@Test
	public void retornaProdutoFornecedorQuandoAlterarComSucesso() {
		produtoFornecedorValida.setId(idProdutoFornecedorExistente);
		Mockito.when(repository.findById(idProdutoFornecedorExistente)).thenReturn(Optional.of(produtoFornecedorValida));
		ProdutoFornecedor produtoFornecedor = service.editarProdutoFornecedor(idProdutoFornecedorExistente, produtoFornecedorValida);
		Assertions.assertNotNull(produtoFornecedor);
		Assertions.assertEquals(idProdutoFornecedorExistente, produtoFornecedor.getId());
		Assertions.assertEquals(produtoFornecedorValida.getPreco(), produtoFornecedor.getPreco());
		Assertions.assertEquals(produtoFornecedorValida.getProduto(), produtoFornecedor.getProduto());
		Assertions.assertEquals(produtoFornecedorValida.getFornecedor(), produtoFornecedor.getFornecedor());
		Mockito.verify(repository).save(produtoFornecedorValida);
	}
	
	@Test
	public void retornaNadaAoExcluirComIdProdutoFornecedorExistente() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluirProdutoFornecedor(idProdutoFornecedorExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idProdutoFornecedorExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdProdutoFornecedorNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.excluirProdutoFornecedor(idProdutoFornecedorNaoExistente);
		});
	}
	
	@Test
	public void consultarPorIdRetornaProdutoFornecedor() {
		ProdutoFornecedor produtoFornecedor = service.consultarProdutoFornecedorId(idProdutoFornecedorExistente);
		Assertions.assertNotNull(produtoFornecedor);
		
	}
	
	@Test 
	public void lançaEntidadeNaoEncontradaQuandoConsultaidProdutoFornecedorNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarProdutoFornecedorId(idProdutoFornecedorNaoExistente);
			});
		Mockito.verify(repository,Mockito.times(1)).findById(idProdutoFornecedorNaoExistente);
	}

}
