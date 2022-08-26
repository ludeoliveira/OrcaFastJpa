package com.orcafastjpa.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orcafastjpa.entidades.Usuario;
import com.orcafastjpa.service.UsuarioService;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTestes {
	
	private Long idUsuarioExistente;
	private Long idUsuarioNaoExistente;
	
	private Usuario usuarioExistente;
	private Usuario usuarioNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private  UsuarioService service;
	
	@Autowired
		private ObjectMapper objectMapper;
	
	@BeforeEach
	void setup() {
		
		idUsuarioExistente = 1l;
		idUsuarioNaoExistente = 1000l;
		
		
		usuarioNovo = new Usuario();
		
		usuarioExistente = new Usuario("MM Materiais de construção", "22.345.989/0001-90");
		usuarioExistente.setId(1l);	
					 
	}
	
	@Test
	public void deveRetornarUsuarioQuandoConsultaIdExistente() throws Exception {
		Mockito.when(service.consultarUsuarioById(idUsuarioExistente)).thenReturn(usuarioExistente);
		ResultActions result = mockMvc.perform(get("/usuario/{idusuario}", idUsuarioExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveRetornar404QuandoConsultaidUsuarioNaoExistente() throws Exception {
		Mockito.doThrow(EntityNotFoundException.class).when(service).consultarUsuarioById(idUsuarioNaoExistente);
		ResultActions result = mockMvc.perform(get("/usuario/{idusuario}", idUsuarioNaoExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void deveRetornar201AoSalvarUsuarioComSucesso() throws Exception {
	Mockito.when(service.salvar(any())).thenReturn(usuarioExistente);
		String jsonBody = objectMapper.writeValueAsString(usuarioNovo);
		ResultActions result = mockMvc.perform(post("/usuario")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	
	@Test
	public void retornaOkQuandoEditaUsuario() throws Exception {
		Mockito.when(service.editarUsuario(eq(idUsuarioExistente), any())).thenReturn(usuarioExistente);
		String jsonBody = objectMapper.writeValueAsString(usuarioExistente);
		ResultActions result = mockMvc.perform(put("/usuario/{idusuario}", idUsuarioExistente)
				.content(jsonBody).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void retorna404QuandoAlteraUsuarioComIdInexistente() throws Exception {
		Mockito.when(service.editarUsuario(eq(idUsuarioNaoExistente), any())).thenThrow(EntityNotFoundException.class);
		String jsonBody = objectMapper.writeValueAsString(usuarioNovo);
		ResultActions result = mockMvc.perform(put("/usuario/{idusuario}", idUsuarioNaoExistente).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void retornaListaConsultaTodosOsUsuarios() throws Exception {
		ResultActions result = mockMvc.perform(get("/usuario"));
		result.andExpect(status().isOk());
		Mockito.when(service.consultarUsuario()).thenReturn(new ArrayList<>());
	}

}
