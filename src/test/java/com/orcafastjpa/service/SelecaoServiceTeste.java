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

import com.orcafastjpa.entidades.Orcamento;
import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.entidades.Selecao;
import com.orcafastjpa.repository.SelecaoRepository;


@ExtendWith(SpringExtension.class)
public class SelecaoServiceTeste {
	
	private Long idSelExistente;
	private Long idSelNaoExistente;
	
	private Selecao selecaoValida;
	private Selecao selecaoInvalida;
	
	private Produto produto;
	private Orcamento orcamento; 
	
	
	@BeforeEach
	void setup() {
		idSelExistente = 1l;
		idSelNaoExistente = 1000l;
		
		orcamento = new Orcamento();
		
		produto = new Produto();
		
		selecaoValida = new Selecao(4, 28.90, produto, orcamento);
		selecaoInvalida = new Selecao(4, 28.90, null, orcamento);

		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(selecaoInvalida);
		Mockito.when(repository.save(selecaoValida)).thenReturn(selecaoValida);
		
		Mockito.doNothing().when(repository).deleteById(idSelExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idSelNaoExistente);
		
		Mockito.when(repository.findById(idSelExistente)).thenReturn(Optional.of(new Selecao()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idSelNaoExistente);
		
				
	}
	
	@InjectMocks
	SelecaoService service;
	
	@Mock
	SelecaoRepository repository;
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(selecaoInvalida));
		Mockito.verify(repository).save(selecaoInvalida);
	}
	
	@Test
	public void retornaSelecaoQuandoSalvarComSucesso() {
		Selecao selecao = service.salvar(selecaoValida);
		Assertions.assertNotNull(selecao);
		Mockito.verify(repository).save(selecaoValida);
	}
	
//	@Test
//	public void retornaExcecaoQuandoAlteraSemSucesso() {
//		selecaoInvalida.setId(idSelExistente);
//		Mockito.when(repository.findById(idSelExistente)).thenReturn(Optional.of(selecaoInvalida));
//		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.editarSelecao(idSelExistente, selecaoInvalida));
//		Mockito.verify(repository).save(selecaoInvalida);
//		
//	}
	
//	@Test
//	public void retornaSelecaoQuandoAlterarComSucesso() {
//		selecaoValida.setId(idSelExistente);
//		Mockito.when(repository.findById(idSelExistente)).thenReturn(Optional.of(selecaoValida));
//		Selecao selecao = service.editarSelecao(idSelExistente, selecaoValida);
//		Assertions.assertNotNull(selecao);
//		Assertions.assertEquals(idSelExistente, selecao.getId());
//		Assertions.assertEquals(selecaoValida.getOrcamento(), selecao.getOrcamento());
//		Assertions.assertEquals(selecaoValida.getPreco(), selecao.getPreco());
//		Assertions.assertEquals(selecaoValida.getProduto(), selecao.getProduto());
//		Assertions.assertEquals(selecaoValida.getQuantidade(), selecao.getQuantidade());
//		Mockito.verify(repository).save(selecaoValida);
//	}
	
	@Test
	public void retornaNadaAoExcluirComIdSelExistente() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluirSelecao(idSelExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idSelExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdSelNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.excluirSelecao(idSelNaoExistente);
		});
	}
	
//	@Test
//	public void consultarPorIdRetornaSelecao() {
//		Selecao sel = service.consultarSelecaoPorId(idSelExistente);
//		Assertions.assertNotNull(sel);
//		
//	}
	
	@Test 
	public void lançaEntidadeNaoEncontradaQuandoConsultaidSelNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarSelecaoPorId(idSelNaoExistente);
			});
		Mockito.verify(repository,Mockito.times(1)).findById(idSelNaoExistente);
	}
	

}
