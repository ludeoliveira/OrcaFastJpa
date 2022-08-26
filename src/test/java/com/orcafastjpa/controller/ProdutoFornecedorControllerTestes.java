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
import com.orcafastjpa.entidades.Produto;
import com.orcafastjpa.entidades.ProdutoFornecedor;
import com.orcafastjpa.service.ProdutoFornecedorService;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoFornecedorControllerTestes {
	
	private Long idExistente;
	private Long idNaoExistente;
	
	private ProdutoFornecedor prodFronExistente;
	private ProdutoFornecedor prodFornNovo;
	private Produto produto;
	private Fornecedor fornecedor = new Fornecedor();

	@Autowired
	private ObjectMapper objMapper;
	
	@Autowired
	private MockMvc nockMvc;
	
	@MockBean
	private ProdutoFornecedorService service;
	
	@BeforeEach
	void setup() {
		idExistente = 1L;
		idNaoExistente = 100L;
		
		produto = new Produto();
		
		prodFronExistente = new ProdutoFornecedor(produto, fornecedor, 30.45);
		prodFronExistente.setId(1L);
		
		prodFornNovo = new ProdutoFornecedor();
		
		Mockito.when(service.consultarProdutoFornecedorId(idExistente)).thenReturn(prodFronExistente);
		Mockito.doThrow(EntityNotFoundException.class).when(service).consultarProdutoFornecedorId(idNaoExistente);
		
		Mockito.when(service.salvar(any())).thenReturn(prodFronExistente);
		
		Mockito.when(service.editarProdutoFornecedor(eq(idExistente), any())).thenReturn(prodFronExistente);
		Mockito.when(service.editarProdutoFornecedor(eq(idNaoExistente), any())).thenThrow(EntityNotFoundException.class);
	}
	
	@Test
	void retornaProdFornAoConsultarIdExistente() throws Exception {
		ResultActions result = nockMvc.perform(get("/produtofornecedor/{idprodutofornecedor}", idExistente).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	void deveRetornar404QuandoConsultarProdFornIdNaoExistente() throws Exception {
		ResultActions result = nockMvc.perform(get("/produtofornecedor/{idprodutofornecedor}", idNaoExistente).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	void retornar201SalvaProdFornComSucesso() throws Exception {
		String jsonBody = objMapper.writeValueAsString(prodFornNovo);
		ResultActions result = nockMvc.perform(post("/produtofornecedor").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON) );
		result.andExpect(status().isCreated());
	}
	
	@Test
	void retornaOkQuandoAlterarProdForn() throws Exception {
		String jsonBody = objMapper.writeValueAsString(prodFronExistente);
		ResultActions result = nockMvc.perform(put("/produtofornecedor/{idprodutofornecedor}", idExistente).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isOk());
	}
	
	@Test
	void retorna404QuandoAlteraProdFornInexistente() throws Exception {
		String jsonBody = objMapper.writeValueAsString(prodFornNovo);
		ResultActions result = nockMvc.perform(put("/produtofornecedor/{idprodutofornecedor}", idNaoExistente).content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		result.andExpect(status().isNotFound());
	}
	
	@Test
	void retornaListaConsultaTodosProdForn() throws Exception {
		ResultActions result = nockMvc.perform(get("/produtofornecedor"));
		result.andExpect(status().isOk());
		Mockito.when(service.consultarProdutoFornecedor()).thenReturn(new ArrayList<>());
	}
}
