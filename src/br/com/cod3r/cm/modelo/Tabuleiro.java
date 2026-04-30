package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas;
	
	private List<Campo> campos = new ArrayList<Campo>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		super();
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linha, int coluna) {
		try {
			campos.stream()
			.filter(c -> c.getLinha() == linha && c.getColuna()== coluna)
			.findFirst()
			.ifPresent(c -> c.abrir());
		}
		catch(ExplosaoException e){
			campos.forEach(c -> c.setAberto(true));
			throw e;
			
		}
		
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.stream()
		.filter(c -> c.getLinha() == linha && c.getColuna()== coluna)
		.findFirst()
		.ifPresent(c -> c.alternarMarcacao());
	}
	
	public List<Campo> getCampos(){
		return campos;
	}

	private void gerarCampos() {
		for(int i = 0; i < linhas; i++) {
			for(int j = 0; j<colunas; j++) {
				campos.add(new Campo(i, j));
			}
		}
		
	}
	
	private void associarVizinhos() {
		for(Campo c1 : campos) {
			for(Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
		
	}

	//****metodo passado no curso mesmo que o tabuleiro esteja instanciado com 0 minas,
	//****do/while executar pelo menos uma vez
//	private void sortearMinas() {
//		long minasArmadas = 0;
//		Predicate<Campo> minado = c -> c.isMinado();//verifica se o campo ja esta minado
//		
//		do {
//			int aleatorio = (int)(Math.random()*campos.size());// gera um numero entre 0 e 1, multiplica pelo tamanho do campo
//			campos.get(aleatorio).minar();//mina o campo gerado, ****mesmo que tabuleiro esteja com 0 minas
//			minasArmadas = campos.stream().filter(minado).count();//filtra os campos ja minados
//		}while(minasArmadas<minas);	//enquanto for menor que a quantidade de minas 
//	}
	
	private void sortearMinas() {
		long minasArmadas = 0;
		
		while(minasArmadas< minas) {//enquanto minas armadas for menor que minas
			int aleatorio =(int)(Math.random()*campos.size());//gera um numero entre 0 e 1, multiplica pelo tamanho do campo
			Campo campo = campos.get(aleatorio);//mina o campo gerado
			
			if(!campo.isMinado()) {//verifica se o campo nao esta minado
				campo.minar();//se nao, ele mina
				minasArmadas++;
			}
		}
	}
	
	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}
	
	public void reiniciar() {
		campos.stream().forEach(c-> c.reiniciar());
		sortearMinas();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("  ");
		for(int c = 0; c< colunas; c++) {
			sb.append(" ");
			sb.append(c);
			sb.append(" ");
		}
		
		sb.append("\n");
		
		int i = 0; //indice do tabuleiro
		for(int l = 0; l<linhas; l++) {//percorre as linhas
			sb.append(l);
			sb.append(" ");
			for(int c = 0; c<colunas ; c++) {//percorre as colunas
				sb.append(" ");
				sb.append(campos.get(i));//escreve o que é naquele campo referente ao indice sorteado
				sb.append(" ");
				i++;//incrementa ao indice 
			}
			sb.append("\n");
		}
		return sb.toString();	
	}
	
	
}