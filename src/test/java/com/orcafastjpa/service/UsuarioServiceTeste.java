package com.orcafastjpa.service;

import java.util.ArrayList;
import java.util.List;
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

import com.orcafastjpa.entidades.Usuario;
import com.orcafastjpa.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
public class UsuarioServiceTeste {
	
	private Long idExistente;
	private Long idNaoExistente;
	
	private Usuario usuarioValido;
	private Usuario usuarioInvalido;
	
	private List<Usuario> usuarios;
	
	@BeforeEach
	public void setUp() {
		idExistente = 1l;
		idNaoExistente = 1000l;
		
		usuarioValido = new Usuario("empresa teste", "1234-567");
		usuarioInvalido = new Usuario("empresa teste2", null);
		
		usuarios = new ArrayList<>();
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(usuarioInvalido);
		Mockito.when(repository.save(usuarioValido)).thenReturn(usuarioValido);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new Usuario()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);
		
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
	}
	
	@InjectMocks
	UsuarioService service;
	
	@Mock
	UsuarioRepository repository;
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSucesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(usuarioInvalido));
		Mockito.verify(repository).save(usuarioInvalido);
	}
	
	@Test
	public void retornaUsuarioQuandoSalvarComSucesso() {
		Usuario usuario = service.salvar(usuarioValido);
		Assertions.assertNotNull(usuario);
		Mockito.verify(repository).save(usuarioValido);
	}
	
	@Test
	public void retornaNadaAoExcluirComIdExistente() {
		Assertions.assertDoesNotThrow(() -> {
			service.excluirUsuario(idExistente);
		});
		
		Mockito.verify(repository,Mockito.times(1)).deleteById(idExistente);
	}
	
	@Test 
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.excluirUsuario(idNaoExistente);
		});
		
		Mockito.verify(repository,Mockito.times(1)).deleteById(idNaoExistente);
	}
	
	@Test
	public void consultarPorIdRetornaUsuario() {
		Usuario usuario = service.consultarUsuarioById(idExistente);
		Assertions.assertNotNull(usuario);
		
		Mockito.verify(repository).findById(idExistente);
	}
	
	@Test 
	public void lançaEntidadeNaoEncontradaQuandoConsultaUsuarioNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			service.consultarUsuarioById(idNaoExistente);
		});
		
		Mockito.verify(repository,Mockito.times(1)).findById(idNaoExistente);
	}
	
	@Test
	public void retornaSelecaoQuandoAlteraUsuarioComSucesso() {
		usuarioValido.setId(idExistente);
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(usuarioValido));
		Usuario usuario = service.editarUsuario(idExistente, usuarioValido);
		Assertions.assertNotNull(usuario);
		Assertions.assertEquals(idExistente,usuario.getId());
		Assertions.assertEquals(usuarioValido.getRazaosocial(), usuario.getRazaosocial());
		Assertions.assertEquals(usuarioValido.getCnpj(), usuario.getCnpj());
		Mockito.verify(repository).save(usuarioValido);
	}
	
	@Test
	public void retornaExcecaoQuandoAlteraUsuarioSemSucesso() {
		usuarioInvalido.setId(idExistente);
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(usuarioInvalido));
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.editarUsuario(idExistente, usuarioInvalido));
		Mockito.verify(repository).save(usuarioInvalido);	
	}

	@Test
	public void retornaListaAoConsultarTodos() {
		usuarios = service.consultarUsuario();
		Assertions.assertNotNull(usuarios);
		Mockito.verify(repository).findAll();
	}
}
