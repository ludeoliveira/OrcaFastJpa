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

import com.orcafastjpa.entidades.Categoria;
import com.orcafastjpa.repository.CategoriaRepository;

@ExtendWith(SpringExtension.class)
public class CategoriaServiceTeste {
	
	private Long idCategoriaExistente;
	private Long idCategoriaNaoExistente;
	
	private Categoria categoriaValida;
	private Categoria categoriaInvalida;
	
	@BeforeEach
	void setup() {
		idCategoriaExistente = 1l;
		idCategoriaNaoExistente = 1000l;
		
		categoriaValida = new Categoria("Basico");
		categoriaInvalida = new Categoria(null);
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(categoriaInvalida);
		Mockito.when(repository.save(categoriaValida)).thenReturn(categoriaValida);
	
		Mockito.doNothing().when(repository).deleteById(idCategoriaExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idCategoriaNaoExistente);

		Mockito.when(repository.findById(idCategoriaExistente)).thenReturn(Optional.of(new Categoria()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idCategoriaNaoExistente);	

	}
	
	@InjectMocks
	CategoriaService service;
	
	@Mock
	CategoriaRepository repository;
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(categoriaInvalida));
		Mockito.verify(repository).save(categoriaInvalida);
	}
	
	@Test
	public void retornaCategoriaQuandoSalvarComSucesso() {
		Categoria categoria = service.salvar(categoriaValida);
		Assertions.assertNotNull(categoria);
		Mockito.verify(repository).save(categoriaValida);
	}
	
	@Test
	public void retornaExcecaoQuandoAlteraSemSucesso() {
		categoriaInvalida.setId(idCategoriaExistente);
		Mockito.when(repository.findById(idCategoriaExistente)).thenReturn(Optional.of(categoriaInvalida));
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.editarCategoria(idCategoriaExistente, categoriaInvalida));
		Mockito.verify(repository).save(categoriaInvalida);
	}
	
	@Test
	public void retornaCategoriaQuandoAlterarComSucesso() {
		categoriaValida.setId(idCategoriaExistente);
		Mockito.when(repository.findById(idCategoriaExistente)).thenReturn(Optional.of(categoriaValida));
		Categoria categoria = service.editarCategoria(idCategoriaExistente, categoriaValida);
		Assertions.assertNotNull(categoria);
		Assertions.assertEquals(idCategoriaExistente, categoria.getId());
		Assertions.assertEquals(categoriaValida.getDescricaoc(), categoria.getDescricaoc());
		Mockito.verify(repository).save(categoriaValida);
	}
	
	@Test
	public void retornaNadaAoExcluirComIdCategoriaExistente() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluirCategoria(idCategoriaExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idCategoriaExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdcategoriaNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.excluirCategoria(idCategoriaNaoExistente);
		});
	}
	
	@Test
	public void consultarPorIdRetornaCategoria() {
		Categoria categoria = service.consultarCategoriaId(idCategoriaExistente);
		Assertions.assertNotNull(categoria);
	}
	
	@Test 
	public void lancaEntidadeNaoEncontradaQuandoConsultaidCategoriaNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarCategoriaId(idCategoriaNaoExistente);
			});
		Mockito.verify(repository,Mockito.times(1)).findById(idCategoriaNaoExistente);
	}
}
