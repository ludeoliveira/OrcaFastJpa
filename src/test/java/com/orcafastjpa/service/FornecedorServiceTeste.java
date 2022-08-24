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
import com.orcafastjpa.repository.FornecedorRepository;

@ExtendWith(SpringExtension.class)
public class FornecedorServiceTeste {
	
	private Long idFornExistente;
	private Long idFornNaoExiste;
	
	private Fornecedor fornecedorValido;
	private Fornecedor fornecedorInvalido;
	
	@BeforeEach
	void setup() {
		idFornExistente = 1l;
		idFornNaoExiste = 1000l;
		
		fornecedorValido = new Fornecedor("MM Construtora", "11.345.878/0001-23", "contatomm@gmail.com", "49 999999999");
		fornecedorInvalido = new Fornecedor("MM Construtora", "11.345.878/0001-23", "contatomm@gmail.com", "49 999999999");

		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(fornecedorInvalido);
		Mockito.when(repository.save(fornecedorValido)).thenReturn(fornecedorValido);
	
		Mockito.doNothing().when(repository).deleteById(idFornExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idFornNaoExiste);
	
		Mockito.when(repository.findById(idFornExistente)).thenReturn(Optional.of(new Fornecedor()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idFornNaoExiste);	
	}
	
	@InjectMocks
	FornecedorService service;
	
	@Mock
	FornecedorRepository repository;
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(fornecedorInvalido));
		Mockito.verify(repository).save(fornecedorInvalido);
	}
	
	@Test
	public void retornaFornecedorQuandoSalvarComSucesso() {
		Fornecedor fornecedor = service.salvar(fornecedorValido);
		Assertions.assertNotNull(fornecedor);
		Mockito.verify(repository).save(fornecedorValido);
	}
	
	@Test
	public void retornaExcecaoQuandoAlteraSemSucesso() {
		fornecedorInvalido.setId(idFornExistente);
		Mockito.when(repository.findById(idFornExistente)).thenReturn(Optional.of(fornecedorInvalido));
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.editarFornecedor(idFornExistente, fornecedorInvalido));
		Mockito.verify(repository).save(fornecedorInvalido);
	}
	
	@Test
	public void retornaFornecedorQuandoAlterarComSucesso() {
		fornecedorValido.setId(idFornExistente);
		Mockito.when(repository.findById(idFornExistente)).thenReturn(Optional.of(fornecedorValido));
		Fornecedor fornecedor = service.editarFornecedor(idFornExistente, fornecedorValido);
		Assertions.assertNotNull(fornecedor);
		Assertions.assertEquals(idFornExistente, fornecedor.getId());
		Assertions.assertEquals(fornecedorValido.getRazaosocial(), fornecedor.getRazaosocial());
		Assertions.assertEquals(fornecedorValido.getCnpj(), fornecedor.getCnpj());
		Assertions.assertEquals(fornecedorValido.getEmail(), fornecedor.getEmail());
		Assertions.assertEquals(fornecedorValido.getTelefone(), fornecedor.getTelefone());
		Mockito.verify(repository).save(fornecedorValido);
	}
	
	@Test
	public void retornaNadaAoExcluirComIdFornExistente() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluirFornecedor(idFornExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idFornExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdFornNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.excluirFornecedor(idFornNaoExiste);
		});
	}
	
	@Test
	public void consultarPorIdRetornaFornecedor() {
		Fornecedor forn = service.consultarFornecedorId(idFornExistente);
		Assertions.assertNotNull(forn);
	}
	
	@Test 
	public void lancaEntidadeNaoEncontradaQuandoConsultaidFornNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarFornecedorId(idFornNaoExiste);
			});
		Mockito.verify(repository,Mockito.times(1)).findById(idFornNaoExiste);
	}
}
