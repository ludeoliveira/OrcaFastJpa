package com.orcafastjpa.service;

import java.time.Instant;
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
import com.orcafastjpa.entidades.Usuario;
import com.orcafastjpa.repository.OrcamentoRepository;

@ExtendWith(SpringExtension.class)
public class OrcamentoServiceTeste {

	private Long idOrcamentoExistente;
	private Long idOrcamentoNaoExistente;
	
	private Orcamento orcamentoValido;
	private Orcamento orcamentoInvalido;
	
	private Usuario usuario;
	private Instant datacriacao;
	
	@BeforeEach
	void setup() {
		idOrcamentoExistente = 1l;
		idOrcamentoNaoExistente = 1000l;
		
		usuario = new Usuario();
		
		orcamentoValido = new Orcamento(usuario, datacriacao);
		orcamentoInvalido = new Orcamento(usuario, null);
	
		Mockito.when(repository.save(orcamentoValido)).thenReturn(orcamentoValido);
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(orcamentoInvalido);
	
		Mockito.when(repository.findById(idOrcamentoExistente)).thenReturn(Optional.of(new Orcamento()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idOrcamentoNaoExistente);
	
		Mockito.doNothing().when(repository).deleteById(idOrcamentoExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idOrcamentoNaoExistente);

	}
	
	@InjectMocks
	OrcamentoService service;
	
	@Mock
	OrcamentoRepository repository;
	
	@Test
	public void retornaOrcamentoQuandoSalvarComSucesso() {
		Orcamento orcamento = service.salvar(orcamentoValido);
		Assertions.assertNotNull(orcamento);
		Mockito.verify(repository).save(orcamentoValido);
	}
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(orcamentoInvalido));
		Mockito.verify(repository).save(orcamentoInvalido);
	}
	
	@Test
	public void retornaOrcamentoQuandoAlterarComSucesso() {
		orcamentoValido.setId(idOrcamentoExistente);
		Mockito.when(repository.findById(idOrcamentoExistente)).thenReturn(Optional.of(orcamentoValido));
		Orcamento orcamento = service.editarOrcamento(idOrcamentoExistente, orcamentoValido);
		Assertions.assertNotNull(orcamento);
		Assertions.assertEquals(idOrcamentoExistente, orcamento.getId());
		Assertions.assertEquals(orcamentoValido.getdatacriacao(), orcamento.getdatacriacao());
		Assertions.assertEquals(orcamentoValido.getUsuario(), orcamento.getUsuario());
		Mockito.verify(repository).save(orcamentoValido);
	}
	
	@Test
	public void retornaExcecaoQuandoAlteraSemSucesso() {
		orcamentoInvalido.setId(idOrcamentoExistente);
		Mockito.when(repository.findById(idOrcamentoExistente)).thenReturn(Optional.of(orcamentoInvalido));
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.editarOrcamento(idOrcamentoExistente, orcamentoInvalido));
		Mockito.verify(repository).save(orcamentoInvalido);
	}

	@Test
	public void retornaNadaAoExcluirComIdOrcamentoExistente() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluirOrcamento(idOrcamentoExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idOrcamentoExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdOrcamentoNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.excluirOrcamento(idOrcamentoNaoExistente);
		});
	}
	
	@Test
	public void consultarPorIdRetornaOrcamento() {
		Orcamento orcamento = service.consultarOrcamentoById(idOrcamentoExistente);
		Assertions.assertNotNull(orcamento);		
	}
	
	@Test 
	public void lançaEntidadeNaoEncontradaQuandoConsultaidOrcamentoNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarOrcamentoById(idOrcamentoNaoExistente);
			});
		Mockito.verify(repository,Mockito.times(1)).findById(idOrcamentoNaoExistente);
	}
}
