package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CampoTeste {

	private Campo campo; //associaçao com classe campo
	
	@BeforeEach // antes de cada teste metodo iniciado de novo
	void iniciarCampo() {
		campo = new Campo(3, 3); //instancia campo
	}
	
	@Test
	void testeVizinhoEsquerda() {
		Campo vizinho = new Campo(3,2); // campo esquerda onde deltaGeral é 1
		boolean resultado = campo.adicionarVizinho(vizinho); //se metodo true vizinho = esquerda 
		assertTrue(resultado);//espera um true obrigatoriamente
	}
	
	@Test
	void testeVizinhoDireita() {
		Campo vizinho = new Campo(3,4); // campo direita onde deltaGeral é 1
		boolean resultado = campo.adicionarVizinho(vizinho); //se metodo true vizinho = direita 
		assertTrue(resultado);//espera um true obrigatoriamente
	}
	
	@Test
	void testeVizinhoEmCima() {
		Campo vizinho = new Campo(2,3); // campo em cima onde deltaGeral é 1
		boolean resultado = campo.adicionarVizinho(vizinho); //se metodo true vizinho = em cima 
		assertTrue(resultado);//espera um true obrigatoriamente
	}
	
	@Test
	void testeVizinhoEmbaixo() {
		Campo vizinho = new Campo(4,3); // campo embaixo onde deltaGeral é 1
		boolean resultado = campo.adicionarVizinho(vizinho); //se metodo true vizinho = embaixo 
		assertTrue(resultado);//espera um true obrigatoriamente
	}

	@Test
	void testeDiagonal() {
		Campo vizinho = new Campo(2,2); // campo diagonal esquerda onde deltaGeral é 2
		boolean resultado = campo.adicionarVizinho(vizinho); //se metodo true vizinho = diagonal 
		assertTrue(resultado);//espera um true obrigatoriamente
	}
	
	@Test
	void testeNaoVizinho() {
		Campo vizinho = new Campo(1,1); // campo onde deltaGeral não é nem 1 nem 2
		boolean resultado = campo.adicionarVizinho(vizinho); //se metodo false vizinho = não vizinho 
		assertFalse(resultado);//espera um false obrigatoriamente
	}
}
