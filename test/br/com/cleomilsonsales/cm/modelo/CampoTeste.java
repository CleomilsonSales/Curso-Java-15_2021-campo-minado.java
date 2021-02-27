package br.com.cleomilsonsales.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CampoTeste {
	
	private Campo campo;
	
	//@BeforeEach - exigi que ao reiniciar o teste o metodo seja iniciado
	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}
	
	//colunas e linha
	@Test
	void testeVizinhoDistEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeVizinhoDistDiagonal() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertTrue(resultado);
	}
	
	@Test
	void testeNaoEVizinho() {
		Campo vizinho = new Campo(1, 1);
		boolean resultado = campo.adicionarVizinhos(vizinho);
		assertFalse(resultado);
	}

}
