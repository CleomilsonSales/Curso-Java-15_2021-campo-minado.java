package br.com.cleomilsonsales.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cleomilsonsales.cm.excecao.ExplosaoException;
import br.com.cleomilsonsales.cm.excecao.SairException;
import br.com.cleomilsonsales.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
	
	private Tabuleiro tabuleiro;
	private Scanner entrada =  new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		execultarJogo();
	}

	private void execultarJogo() {
		try {
			boolean continuar = true;
			
			//Regras do jogo
			System.out.println("Regras: Deverá informa qual campo com coordenadas x e y e depois se sera aberto ou (des)marcado");
			System.out.println("Ao abrir um campo sera aberta vizinhança desse campo, se a vizinha for segura continuar sendo aberto");
			System.out.println("Ao abrir um campos vizinhos e for localizado uma mina na vizinha, sera truncada a abertura de campos");
			System.out.println("O campos proximo de minas serão sinalizadas com quantidade de minas na vizinhança");
			System.out.println("Ao marca todas as minas e abrir todos os campos seguros, vencerá o jogo.");
			System.out.println();
			
			while(continuar) {
				
				cicloDoJogo();
				
				System.out.println("Outra partida? (S/n) ");
				String resposta = entrada.nextLine();
				
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				}else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Tchau!");
		}finally{
			entrada.close();
		}
	}

	private void cicloDoJogo() {
		try {
			while(!tabuleiro.objetivoAlcancado()) {
				System.out.println("Existem "+tabuleiro.getMinas()+" minas");
				
				System.out.println(tabuleiro);
				
				String digitado = capturarValorDigitado("Digite o x e y do campo (Ex.: 2,3): ");
				
				Iterator<Integer> xy = Arrays.stream(digitado.split(","))
						.map(e -> Integer.parseInt(e.trim()))
						.iterator();
				digitado = capturarValorDigitado("1 - Abrir ou 2 - (Des)Marcar: ");
				System.out.println();
		
				if("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				}else if("2".equals(digitado)) {
					tabuleiro.alterarMarcacao(xy.next(), xy.next());
				}
			}
			System.out.println(tabuleiro);
			System.out.println("Você ganhou!");
		} catch (ExplosaoException e) {
			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		} catch (Exception e) {
			System.out.println("Informação inválida!");
			throw new SairException();
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.print("S-para sair | "+texto);
		String digitado = entrada.nextLine();
		
		if ("s".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}

}
