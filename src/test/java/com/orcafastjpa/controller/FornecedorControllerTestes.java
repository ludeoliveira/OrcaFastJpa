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
import com.orcafastjpa.entidades.Fornecedor;
import com.orcafastjpa.service.FornecedorService;

@SpringBootTest
@AutoConfigureMockMvc
public class FornecedorControllerTestes {
	
	private Long idFornExistente;
	private Long idFornNaoExistente;
	private Fornecedor fornecedorExistente;
	private Fornecedor fornecedorNovo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private  FornecedorService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setup() {
		idFornExistente = 1l;
		idFornNaoExistente = 1000l;
		fornecedorExistente = new Fornecedor("MM Construtora", "11.345.878/0001-23", "contatomm@gmail.com", "(49)9999-99999");
		fornecedorNovo = new Fornecedor();
		fornecedorExistente.setId(1l);
	
		Mockito.when(service.consultarFornecedorId(idFornExistente)).thenReturn(fornecedorExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(service).consultarFornecedorId(idFornNaoExistente);
	}
	
	@Test
	public void deveRetornarFornecedorQuandoConsultaIdExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/fornecedor/{idfornecedor}", idFornExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void deveRetornar404QuandoConsultaIdNaoExistente() throws Exception {
		ResultActions result = mockMvc.perform(get("/fornecedor/{idfornecedor}", idFornNaoExistente)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test 
	public void deveRetornar201QuandoSalvarComSucesso() throws Exception {
		Mockito.when(service.salvar(any())).thenReturn(fornecedorExistente);
		String jsonBody = objectMapper.writeValueAsString(fornecedorNovo);
		ResultActions result = mockMvc.perform(post("/fornecedor")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isCreated());			
	}
	
	@Test
	public void retornaOkQuandoEditaFornecedor() throws Exception {
		Mockito.when(service.editarFornecedor(eq(idFornExistente), any())).thenReturn(fornecedorExistente);
		String jsonBody = objectMapper.writeValueAsString(fornecedorExistente);
		ResultActions result = mockMvc.perform(put("/fornecedor/{idfornecedor}", idFornExistente)
				.content(jsonBody).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	public void retorna404QuandoAlteraFornecedorComIdInexistente() throws Exception {
		Mockito.when(service.editarFornecedor(eq(idFornNaoExistente), any())).thenThrow(EntityNotFoundException.class);
		String jsonBody = objectMapper.writeValueAsString(fornecedorNovo);
		ResultActions result = mockMvc.perform(put("/fornecedor/{idfornecedor}", idFornNaoExistente).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void retornaListaConsultaTodosFornecedores() throws Exception {
		ResultActions result = mockMvc.perform(get("/fornecedor"));
		result.andExpect(status().isOk());
		Mockito.when(service.consultarFornecedor()).thenReturn(new ArrayList<>());
	}
}

