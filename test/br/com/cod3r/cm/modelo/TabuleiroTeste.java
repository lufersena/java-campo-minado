package br.com.cod3r.cm.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

class TabuleiroTeste {

	private Tabuleiro tabuleiro;
	
	@BeforeEach
	void iniciarTabuleiro() {
		tabuleiro = new Tabuleiro(2, 2, 0);
	}
	
	@Test
	void testeAbrir() {
		tabuleiro.abrir(1, 1);
		
		Campo campo = tabuleiro.getCampos().stream()
				.filter(c -> c.getLinha() == 1 && c.getColuna() == 1)
				.findFirst()
				.orElseThrow();
		
		assertTrue(campo.isAberto());
	
	}
	
	@Test
	void testeAternarMarcacao() {
		tabuleiro.alternarMarcacao(1, 1);
		
		Campo campo = tabuleiro.getCampos().stream()
				.filter(c -> c.getLinha()==1 && c.getColuna()== 1)
				.findFirst()
				.orElseThrow();
		
		assertTrue(campo.isFechado());
		
	}
	
	@Test
	void testeObjetivoAlcancadoMarcadoComMina() {
		Tabuleiro tb2 = new Tabuleiro(1, 1, 1);
		tb2.alternarMarcacao(0, 0);
		
		Campo campo = tb2.getCampos().stream()
				//.filter(c -> c.getLinha() == 0 && c.getColuna() == 0)
				.findFirst()
				.orElseThrow();
		
		assertTrue(tb2.objetivoAlcancado());
		
	}
	
	@Test
	void testeObjetivoAlcancadoNaoMinadoEaberto() {
		Tabuleiro tb3 = new Tabuleiro(1, 1, 0);
		tb3.abrir(0, 0);
		
		Campo campo = tb3.getCampos().stream()
				//.filter(c -> c.getLinha() == 0 && c.getColuna() == 0)
				.findFirst()
				.orElseThrow();

		assertTrue(tb3.objetivoAlcancado());		
	}
	
	@Test
	void testeReiniciarAbrir() {
		Tabuleiro tb4 = new Tabuleiro(1, 1, 1);
		
		Campo campo = tb4.getCampos().stream()
				//.filter(c->c.getLinha() == 0 && c.getColuna() == 0)
				.findFirst()
				.orElseThrow();
		
		assertThrows(ExplosaoException.class, ()->{ //espera uma exception no teste 
			campo.abrir();//estoura a excecao
		});
		
		tb4.reiniciar();
		assertTrue(campo.isFechado());
	}
	
	@Test
	void testeReiniciarMarcar() {
		Tabuleiro tb4 = new Tabuleiro(1, 1, 1);
		
		Campo campo = tb4.getCampos().stream()
				.filter(c->c.getLinha() == 0 && c.getColuna() == 0)
				.findFirst()
				.orElseThrow();
		
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
		tb4.reiniciar();
		assertFalse(campo.isMarcado());	
	}
	
	@Test
	void testeReiniciarMinar() {
		Tabuleiro tb4 = new Tabuleiro(1, 1, 0);
		
		Campo campo = tb4.getCampos().stream()
				//.filter(c->c.getLinha() == 0 && c.getColuna() == 0)
				.findFirst()
				.orElseThrow();
		
		campo.minar();
		assertTrue(campo.isMinado());
		tb4.reiniciar();
		assertFalse(campo.isMinado());	
	}
	
	@Test
	void testeToStringTabuleiro() {
		Tabuleiro tb5 = new Tabuleiro(3, 3, 0);	
	
		String esperado = 
				" ?  ?  ? \n" +
				" ?  ?  ? \n" +
				" ?  ?  ? \n";
		
		String resultado = tb5.toString();
		
		assertEquals(esperado, resultado);
	
	}
	
}
