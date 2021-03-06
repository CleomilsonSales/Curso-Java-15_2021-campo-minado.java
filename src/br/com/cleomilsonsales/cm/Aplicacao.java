package br.com.cleomilsonsales.cm;

import br.com.cleomilsonsales.cm.modelo.Tabuleiro;
import br.com.cleomilsonsales.cm.visao.TabuleiroConsole;

public class Aplicacao {
	public static void main(String[] args) {
		/*
		 * Jogo:
		 * Ao abrir um campo ele ir� abrir os vizinhos que n�o tem bomba.
		 * quando um vizinho estiver bomba ele para de abrir e diz quantas bombas tem na vizinha�a do campo aberto
		 * marcado � para n�o abrir
		 * */
		Tabuleiro tabuleiro = new Tabuleiro(10, 10, 8);
		new TabuleiroConsole(tabuleiro);
		
		/* Testes
		tabuleiro.alterarMarcacao(4, 4);
		tabuleiro.alterarMarcacao(4, 5);
		tabuleiro.abrir(3, 3);
		
		System.out.println(tabuleiro);*/
	}
}
