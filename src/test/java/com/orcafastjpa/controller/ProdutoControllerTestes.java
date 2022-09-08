package com.orcafastjpa.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.orcafastjpa.entidade.dto.ProdutoDTO;
import com.orcafastjpa.entidades.Categoria;
import com.orcafastjpa.service.ProdutoService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTestes {
	
	private Long idProdExistente;
	private Long idProdNaoExistente;
	
	private ProdutoDTO produtoExistente;
	private ProdutoDTO produtoNovo;
	
	private Categoria categoria;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProdutoService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@BeforeEach
	void setup() {
		
		idProdExistente = 1l;
		idProdNaoExistente = 1000l;
		
		categoria = new Categoria();
	
		produtoNovo = new ProdutoDTO();
		
		produtoExistente = new ProdutoDTO(2l, categoria, "Painel Ripado", "Santa Luzia", "imagem");
		produtoExistente.setId(1l);	
					 
	}
	
	@Test
	public void deveRetornarProdutoQuandoConsultaIdExistente() throws Exception {
		Mockito.when(service.consultarProdutoId(idProdExistente)).thenReturn(produtoExistente);
		ResultActions result = mockMvc.perform(get("/produto/{idproduto}", idProdExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveRetornar404QuandoConsultaIdProdNaoExistente() throws Exception {
		Mockito.doThrow(EntityNotFoundException.class).when(service).consultarProdutoId(idProdExistente);
		ResultActions result = mockMvc.perform(get("/produto/{idproduto}", idProdNaoExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
//	@Test
//	public void deveRetornar201AoSalvarProdutoComSucesso() throws Exception {
//	Mockito.when(service.salvarProduto(any())).thenReturn(produtoExistente);
//		String jsonBody = objectMapper.writeValueAsString(produtoNovo);
//		ResultActions result = mockMvc.perform(post("/selecao")
//				.content(jsonBody)
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON));
//		result.andExpect(status().isCreated());
//	}
	
	@Test
	public void retornaOkQuandoEditaProduto() throws Exception {
		Mockito.when(service.editarProduto(eq(idProdExistente), any())).thenReturn(produtoExistente);
		String jsonBody = objectMapper.writeValueAsString(produtoExistente);
		ResultActions result = mockMvc.perform(put("/produto/{idproduto}", idProdExistente)
				.content(jsonBody).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void retorna404QuandoAlteraProdutoComIdInexistente() throws Exception {
		Mockito.when(service.editarProduto(eq(idProdNaoExistente), any())).thenThrow(EntityNotFoundException.class);
		String jsonBody = objectMapper.writeValueAsString(produtoNovo);
		ResultActions result = mockMvc.perform(put("/selecao/{idproduto}", idProdNaoExistente).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void retornaListaConsultaTodosProdutos() throws Exception {
		ResultActions result = mockMvc.perform(get("/produto"));
		result.andExpect(status().isOk());
		Mockito.when(service.consultarProdutos()).thenReturn(new ArrayList<>());
	}

}
