package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

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
	
	@Test
	void testeValorPadraoAtributoMarcado() { //valor padrao false
		assertFalse(campo.isMarcado());//espera um false
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao(); //alterna false para true do campo
		assertTrue(campo.isMarcado());//espera um true
	}
	
	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao(); //alterna de false para true no campo
		campo.alternarMarcacao(); //alterna de true para false no campo
		assertFalse(campo.isMarcado());//espera um false
	}
	
	@Test
	void testeAbrirNaoMinadoNaoMarcado(){
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado(){
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado(){ // campo deve estar marcado e minado
		campo.alternarMarcacao();//altera para marcado
		campo.minar();//coloca uma mina
		assertFalse(campo.abrir()); //espera um false nao da para abrir se estiver marcado
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		
		assertThrows(ExplosaoException.class, ()->{ //espera uma exception no teste 
			campo.abrir();//estoura a excecao
		});
	}
	
	@Test
	void testeAbrirComVizinhos1() {
		
		Campo campo11 = new Campo(1, 1); 
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);//adiciona vizinho campo22 ao campo11
		
		campo.adicionarVizinho(campo22);//campo33 atual adiciona como vizinho campo22
		campo.abrir();//abre todos os campos porque todos estao abertos 
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testeAbrirComVizinhos2() {
		
		Campo campo11 = new Campo(1, 1); 
		Campo campo12 = new Campo(1, 2);
		campo12.minar();//mina o campo12
		
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);//adiciona vizinho campo22 ao campo11
		campo22.adicionarVizinho(campo12);//adiciona vizinho campo22 ao campo12
		
		campo.adicionarVizinho(campo22);//campo33 atual adiciona como vizinho campo22
		campo.abrir();//abre todos os campos ate o campo22 porque a viznhança(campo12) esta minado
		
		assertTrue(campo22.isAberto() && campo11.isFechado());// espera um true (campo22 aberto(true)) e campo11 fechado
	}
	
	@Test
	void testeGetLinhaGetColuna() {
		Campo campo = new Campo(3, 3);
		
		assertEquals(3, campo.getLinha());
		assertEquals(3, campo.getColuna());
	}
	
	@Test
	void testeObjetivoAlcacadoDesvendado() {
		Campo campo = new Campo(3, 3);
		campo.abrir();
		
		assertTrue(campo.objetivoAlcancado());	
	}
	
	@Test
	void testeObjetivoAlcacadoProtegido() {
		Campo campo = new Campo(3, 3);
		campo.minar();
		campo.alternarMarcacao();
		
		assertTrue(campo.objetivoAlcancado());	
	}
	
	@Test
	void testeMinasVizinhanca() {
		Campo campo33 = new Campo(3, 3);
		Campo campo22 = new Campo(2, 2);
		Campo campo23 = new Campo(2, 3);
		Campo campo32 = new Campo(3, 2);
		
		campo33.adicionarVizinho(campo22);
		campo33.adicionarVizinho(campo23);
		campo33.adicionarVizinho(campo32);
		campo22.minar();
		campo23.minar();
		campo32.alternarMarcacao();
		
		assertEquals(2, campo33.minasVizinhanca());
	
	}
	
	@Test
	void testeReiniciar() {
		Campo campo33 = new Campo(3, 3);
		
		campo33.abrir();
		campo33.minar();
		campo33.alternarMarcacao();
		 
		campo33.reiniciar();
		
		assertFalse(campo33.isAberto());
		assertFalse(campo33.isMinado());
		assertFalse(campo33.isMarcado());
	}
	
	@Test
	void testeToStringMarcado() {
		Campo campo33 = new Campo(3, 3);
		
		campo33.alternarMarcacao();
		
		assertEquals("X", campo33.toString());	
	}
	
	@Test
	void testeToStringAbertoEMinado() {
	    Campo campo33 = new Campo(3, 3);

	    campo33.minar();

	    assertThrows(ExplosaoException.class, () -> campo33.abrir());

	    assertTrue(campo33.isAberto());
	    assertEquals("*", campo33.toString());
	}
	
	@Test
	void testeToStringAbertoMinasVizinhanca() {
		Campo campo33 = new Campo(3, 3);
		Campo campo22 = new Campo(2, 2);
		Campo campo23 = new Campo(2, 3);
		Campo campo32 = new Campo(3, 2);
		
		campo33.adicionarVizinho(campo22);
		campo33.adicionarVizinho(campo23);
		campo33.adicionarVizinho(campo32);
		campo22.minar();
		campo23.minar();
		campo32.alternarMarcacao();
		
		campo33.abrir();	
		assertEquals("2", campo33.toString());
	}
	
	@Test
	void testeToStringAbertoOufechado() {
		Campo campo33 = new Campo(3, 3);
		Campo campo22 = new Campo(2, 2);
		
		campo33.abrir();
		
		assertEquals("", campo33.toString());	
		assertEquals("?", campo22.toString());	
	}
}
