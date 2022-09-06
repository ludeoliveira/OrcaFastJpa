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
import com.orcafastjpa.entidades.Orcamento;
import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.entidades.Selecao;
import com.orcafastjpa.service.SelecaoService;

@SpringBootTest
@AutoConfigureMockMvc
public class SelecaoControllerTestes {
	
	private Long idSelExistente;
	private Long idSelNaoExistente;
	
	private Selecao selecaoExistente;
	private Selecao selecaoNova;
	
	private Produto produto;
	private Orcamento orcamento; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private  SelecaoService service;
	
	@Autowired
		private ObjectMapper objectMapper;
	
	@BeforeEach
	void setup() {
		
		idSelExistente = 1l;
		idSelNaoExistente = 1000l;
		
		orcamento = new Orcamento();
		produto = new Produto();
		
		selecaoNova = new Selecao();
		
		selecaoExistente = new Selecao(4, 28.90, produto, orcamento);
		selecaoExistente.setId(1l);	
					 
	}
	
//	@Test
//	public void deveRetornarSelecaoQuandoConsultaIdExistente() throws Exception {
//		Mockito.when(service.consultarSelecaoPorId(idSelExistente)).thenReturn(selecaoExistente);
//		ResultActions result = mockMvc.perform(get("/selecao/{idselecao}", idSelExistente)
//				.accept(MediaType.APPLICATION_JSON));
//		result.andExpect(status().isOk());
//	}
	
	@Test
	public void deveRetornar404QuandoConsultaidSelNaoExistente() throws Exception {
		Mockito.doThrow(EntityNotFoundException.class).when(service).consultarSelecaoPorId(idSelNaoExistente);
		ResultActions result = mockMvc.perform(get("/selecao/{idselecao}", idSelNaoExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	
//	@Test
//	public void deveRetornar201AoSalvarSelecaoComSucesso() throws Exception {
//	Mockito.when(service.salvar(any())).thenReturn(selecaoExistente);
//		String jsonBody = objectMapper.writeValueAsString(selecaoNova);
//		ResultActions result = mockMvc.perform(post("/selecao")
//				.content(jsonBody)
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON));
//		result.andExpect(status().isCreated());
//	}
	
	
//	@Test
//	public void retornaOkQuandoEditaSelecao() throws Exception {
//		Mockito.when(service.editarSelecao(eq(idSelExistente), any())).thenReturn(selecaoExistente);
//		String jsonBody = objectMapper.writeValueAsString(selecaoExistente);
//		ResultActions result = mockMvc.perform(put("/selecao/{idselecao}", idSelExistente)
//				.content(jsonBody).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON));
//		result.andExpect(status().isOk());
//	}
	
	@Test
	public void retorna404QuandoAlteraSelecaoComIdInexistente() throws Exception {
		Mockito.when(service.editarSelecao(eq(idSelNaoExistente), any())).thenThrow(EntityNotFoundException.class);
		String jsonBody = objectMapper.writeValueAsString(selecaoNova);
		ResultActions result = mockMvc.perform(put("/selecao/{idselecao}", idSelNaoExistente).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void retornaListaConsultaTodasSelecoes() throws Exception {
		ResultActions result = mockMvc.perform(get("/selecao"));
		result.andExpect(status().isOk());
		Mockito.when(service.consultarSelecao()).thenReturn(new ArrayList<>());
	}

}
