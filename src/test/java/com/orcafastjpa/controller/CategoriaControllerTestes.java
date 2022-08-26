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
import com.orcafastjpa.entidades.Categoria;
import com.orcafastjpa.service.CategoriaService;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTestes {
	
	private Long idCategoriaExistente;
	private Long idCategoriaNaoExistente;
	
	private Categoria categoriaExistente;
	private Categoria categoriaNova;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private  CategoriaService service;
	
	@Autowired
		private ObjectMapper objectMapper;
	
	
	@BeforeEach
	void setup() {
		
		idCategoriaExistente = 1l;
		idCategoriaNaoExistente = 1000l;
		
		categoriaNova = new Categoria();
		
		categoriaExistente = new Categoria("Basico");
		categoriaExistente.setId(1l);	
					 
	}
	
	@Test
	public void deveRetornarCategoriaQuandoConsultaIdExistente() throws Exception {
		Mockito.when(service.consultarCategoriaId(idCategoriaExistente)).thenReturn(categoriaExistente);
		ResultActions result = mockMvc.perform(get("/categoria/{idcategoria}", idCategoriaExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveRetornar404QuandoConsultaidCategoriaNaoExistente() throws Exception {
		Mockito.doThrow(EntityNotFoundException.class).when(service).consultarCategoriaId(idCategoriaNaoExistente);
		ResultActions result = mockMvc.perform(get("/categoria/{idcategoria}", idCategoriaNaoExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deveRetornar201AoSalvarCategoriaComSucesso() throws Exception {
	Mockito.when(service.salvar(any())).thenReturn(categoriaExistente);
		String jsonBody = objectMapper.writeValueAsString(categoriaNova);
		ResultActions result = mockMvc.perform(post("/categoria")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	@Test
	public void retornaOkQuandoEditaCategoria() throws Exception {
		Mockito.when(service.editarCategoria(eq(idCategoriaExistente), any())).thenReturn(categoriaExistente);
		String jsonBody = objectMapper.writeValueAsString(categoriaExistente);
		ResultActions result = mockMvc.perform(put("/categoria/{idcategoria}", idCategoriaExistente)
				.content(jsonBody).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}

	@Test
	public void retorna404QuandoAlteraCategoriaComIdInexistente() throws Exception {
		Mockito.when(service.editarCategoria(eq(idCategoriaNaoExistente), any())).thenThrow(EntityNotFoundException.class);
		String jsonBody = objectMapper.writeValueAsString(categoriaNova);
		ResultActions result = mockMvc.perform(put("/categoria/{idcategoria}", idCategoriaNaoExistente).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void retornaListaConsultaTodasCategorias() throws Exception {
		ResultActions result = mockMvc.perform(get("/categoria"));
		result.andExpect(status().isOk());
		Mockito.when(service.consultarCategorias()).thenReturn(new ArrayList<>());
	}
}
