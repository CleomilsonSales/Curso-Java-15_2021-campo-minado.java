package br.com.cleomilsonsales.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cleomilsonsales.cm.excecao.ExplosaoException;

public class Campo {
	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinhos(Campo vizinho) {
		//canditados a vizihos
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		// se for dois forem diferente sera uma analise de casa na diagonal
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		//diferença entre as coluna //pegando numero absoluto
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		//analise: para saber se é vinho em casos de colunas e linha a somatoria deverá ser 1 e na diagona deverá ser 2 (isso é a relação logica)
		int deltaGeral = deltaColuna + deltaLinha;
		
		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return  true;
		}else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}else {
			return false;
		}
	}
	
	void alternarMarcacao() {
		//para impedir abrir um campo marcado
		if (!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean abrir() {
		//ação para abrir campo
		if(!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				//exceção para interromper a aplicação
				throw new ExplosaoException();
			}
			
			if (vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		}else {
			return false;
		}
		
	}
	
	void minar() {
		minado =  true;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	//boa pratica: quando for algo boolean não usar o termo GET mas IS
	public boolean isMarcado() {
		return marcado;
	}
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	
	public boolean isAberto() {
		return aberto;
	}
	
	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}
	
	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}
	
	public String toString() {
		if (marcado) {
			return "x";
		}else if (aberto && minado) {
			return "*";
		}else if (aberto && minasNaVizinhanca() > 0) {
			return String.valueOf(minasNaVizinhanca());
		}else if (aberto) {
			return " ";
		}else {
			return "?";
		}
	}
}
