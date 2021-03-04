package br.com.cleomilsonsales.cm;

import br.com.cleomilsonsales.cm.modelo.Tabuleiro;

public class Aplicacao {
	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		
		System.out.println(tabuleiro);
	}
}
