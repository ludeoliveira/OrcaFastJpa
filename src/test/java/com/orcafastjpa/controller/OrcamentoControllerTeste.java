package com.orcafastjpa.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
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
import com.orcafastjpa.entidades.Usuario;
import com.orcafastjpa.service.OrcamentoService;





@SpringBootTest
@AutoConfigureMockMvc
public class OrcamentoControllerTeste {
	
	private Long idOrcamentoExistente;
	private Long idOrcamentoNaoExistente;
	
	private Orcamento orcamentoExistente;
	private Orcamento orcamentoNovo;
	
	private Usuario usuario;
	private Instant datacriacao;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private  OrcamentoService service;
	
	@Autowired
		private ObjectMapper objectMapper;
	
	@BeforeEach
	void setup() {
		idOrcamentoExistente = 1l;
		idOrcamentoNaoExistente = 1000l;
		
		usuario = new Usuario();
		
		orcamentoNovo = new Orcamento();
		
		orcamentoExistente = new Orcamento(usuario, datacriacao);
		orcamentoExistente.setId(1l);

	}
	@Test
	public void deveRetornarOrcamentoQuandoConsultaIdExistente() throws Exception {
		Mockito.when(service.consultarOrcamentoById(idOrcamentoExistente)).thenReturn(orcamentoExistente);
		ResultActions result = mockMvc.perform(get("/orcamento/{idorcamento}", idOrcamentoExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveRetornar404QuandoConsultaidOrcamentoNaoExistente() throws Exception {
		Mockito.doThrow(EntityNotFoundException.class).when(service).consultarOrcamentoById(idOrcamentoNaoExistente);
		ResultActions result = mockMvc.perform(get("/orcamento/{idorcamento}", idOrcamentoNaoExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void deveRetornar201AoSalvarOrcamentoComSucesso() throws Exception {
	Mockito.when(service.salvar(any())).thenReturn(orcamentoExistente);
		String jsonBody = objectMapper.writeValueAsString(orcamentoNovo);
		ResultActions result = mockMvc.perform(post("/orcamento")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());
	}
	
	
	@Test
	public void retornaOkQuandoEditaOrcamento() throws Exception {
		Mockito.when(service.editarOrcamento(eq(idOrcamentoExistente), any())).thenReturn(orcamentoExistente);
		String jsonBody = objectMapper.writeValueAsString(orcamentoExistente);
		ResultActions result = mockMvc.perform(put("/orcamento/{idorcamento}", idOrcamentoExistente)
				.content(jsonBody).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void retorna404QuandoAlteraOrcamentoComIdInexistente() throws Exception {
		Mockito.when(service.editarOrcamento(eq(idOrcamentoNaoExistente), any())).thenThrow(EntityNotFoundException.class);
		String jsonBody = objectMapper.writeValueAsString(orcamentoNovo);
		ResultActions result = mockMvc.perform(put("/orcamento/{idorcamento}", idOrcamentoNaoExistente).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	

	
	@Test
	public void retornaListaConsultaTodosOrcamentos() throws Exception {
		ResultActions result = mockMvc.perform(get("/orcamento"));
		result.andExpect(status().isOk());
		Mockito.when(service.consultarOrcamentos()).thenReturn(new ArrayList<>());
	}

	
}
	
	
	
