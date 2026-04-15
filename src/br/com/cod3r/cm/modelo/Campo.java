package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean aberto;
	private boolean minado;
	private boolean marcado;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha; // true se diferente 
		boolean colunaDiferente  = coluna != vizinho.coluna; // true se diferente
		boolean diagonal = linhaDiferente && colunaDiferente; // true se ambas true
		
		int deltaLinha = Math.abs(linha - vizinho.linha); // valor absoluto de linha - linha (1 || 0)
		int deltaColuna = Math.abs(coluna - vizinho.coluna);// valor absolute de coluna - coluna (1 || 0) 
		int deltaGeral = deltaColuna + deltaLinha;// linha + coluna = 1 vizinho(cruz) se = 2 vizinho diagonal 
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho); // adiciona vizinho na lista cruz
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho); // adiciona vizinho na lista diagonal
			return true;
		} else {
			return false;
		}
	}
	
	
}
